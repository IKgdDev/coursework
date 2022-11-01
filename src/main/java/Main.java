import Basket.Category;
import Basket.Product;
import Server.Request;
import Server.Statistics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Product> products;
        try (FileReader reader = new FileReader("categories.tsv")) {
            CsvToBean<Product> csv = new CsvToBeanBuilder<Product>(reader)
                    .withSeparator('\t')
                    .withMappingStrategy(getStrategy())
                    .build();
            products = csv.parse();
        }

        Map<String, Category> basket = new HashMap<>();
        basket.put("другое", new Category("другое", 0));
        for (Product product : products) {
            String category = product.getCategory();
            if (!basket.containsKey(category)) {
                basket.put(category, new Category(category, 0));
            }
        }

        Statistics statistics = new Statistics();

        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            while (true) {
                try (
                        //Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new FileReader("request.json"));
                        PrintWriter out = new PrintWriter("reply.json")
                ) {
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    Request request = gson.fromJson(in.readLine(), Request.class);

                    String strCategory = "другое";

                    for (Product product : products) {
                        String category = product.getCategory(request.getTitle());
                        if (category != null){
                            strCategory = category;
                            break;
                        }
                    }

                    basket.get(strCategory).addSum(request.getSum());
                    statistics.setMaxCategory(new ArrayList<>(basket.values()));

                    out.write(gson.toJson(statistics));
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
