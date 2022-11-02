package server;

import basket.Category;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.*;

public class Statistics implements Serializable {
    @Expose
    private Category maxCategory;
    private Map<String, Category> categories;

    public Statistics(Map<String, Category> categories) {
        this.categories = categories;
    }

    public Statistics() {
    }

    public void setMaxCategory(){
        Optional<Category> category = categories.values()
                .stream()
                .max(Comparator.comparingInt(Category::getSum));
        category.ifPresent(value -> maxCategory = value);
        System.out.println();
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categoryList) {
        Map<String, Category> categories = new HashMap<>();
        for (Category category : categoryList) {
            categories.put(category.getCategory(), category);
        }
        this.categories = categories;
    }

    public Category getMaxCategory() {
        return maxCategory;
    }
}
