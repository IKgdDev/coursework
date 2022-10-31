import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Basket basket = Basket.loadFromTcv(new File("categories.tsv"));
    }
}
