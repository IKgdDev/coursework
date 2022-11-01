package server;

import basket.Category;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Statistics {
    private Category maxCategory;

    public void setMaxCategory(List<Category> categoryList){
        Optional<Category> category = categoryList
                .stream()
                .max(Comparator.comparingInt(Category::getSum));
        category.ifPresent(value -> maxCategory = value);
    }

    public Category getMaxCategory() {
        return maxCategory;
    }
}
