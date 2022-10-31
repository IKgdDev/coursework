import Basket.Category;
import Basket.Product;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Product> products;
        try (FileReader reader = new FileReader(new File("categories.tsv"))) {
            CsvToBean<Product> csv = new CsvToBeanBuilder<Product>(reader)
                    .withSeparator('\t')
                    .withMappingStrategy(getStrategy())
                    .build();
            products = csv.parse();
        }

        Map<String, Category> categories = new HashMap<>();
        for (Product product : products) {
            categories.put(product.getCategory(), new Category(product.getCategory(), 0));
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
