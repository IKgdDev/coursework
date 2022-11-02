import basket.Category;
import basket.Product;
import server.Request;
import server.Statistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        List<Product> products;
        try (FileReader reader = new FileReader("categories.tsv")) {
            CsvToBean<Product> csv = new CsvToBeanBuilder<Product>(reader)
                    .withSeparator('\t')
                    .withMappingStrategy(getStrategy())
                    .build();
            products = csv.parse();
        }

        File dataFile = new File("data.bin");

        Statistics statistics = null;

        if (dataFile.exists()) {
            try (FileInputStream fis = new FileInputStream(dataFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                statistics = (Statistics) ois.readObject();
            }
        } else {
            Map<String, Category> categories = new HashMap<>();
            categories.put("другое", new Category("другое", 0));
            for (Product product : products) {
                String category = product.getCategory();
                if (!categories.containsKey(category)) {
                    categories.put(category, new Category(category, 0));
                }
            }
            statistics = new Statistics(categories);
        }

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();


        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            while (true) {
                System.out.println();
                try (
                        //Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new FileReader("request.json"));
                        PrintWriter out = new PrintWriter("reply.json");
                        FileOutputStream fos = new FileOutputStream(dataFile);
                        ObjectOutputStream oos = new ObjectOutputStream(fos)
                ) {
                    Request request = gson.fromJson(in.readLine(), Request.class);

                    String requestCategory = "другое";

                    for (Product product : products) {
                        if (product.getTitle().equals(request.getTitle())) {
                            requestCategory = product.getCategory();
                            break;
                        }
                    }

                    statistics.getCategories().get(requestCategory).addSum(request.getSum());

                    statistics.setMaxCategory();

                    out.write(gson.toJson(statistics));

                    oos.writeObject(statistics);

                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private static ColumnPositionMappingStrategy<Product> getStrategy() {
        ColumnPositionMappingStrategy<Product> strategy =
                new ColumnPositionMappingStrategy<>();
        strategy.setType(Product.class);
        strategy.setColumnMapping("title", "category");
        return strategy;
    }
}
