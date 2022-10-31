import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Basket {
    private List<Product> productList;

    private Basket(List<Product> productList) {
        this.productList = productList;
    }

    public static Basket loadFromTcv(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            CsvToBean<Product> csv = new CsvToBeanBuilder<Product>(reader)
                    .withSeparator('\t')
                    .withMappingStrategy(getStrategy())
                    .build();
            return new Basket(csv.parse());
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
