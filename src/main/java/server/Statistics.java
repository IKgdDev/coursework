package server;

import basket.Category;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.*;

public class Statistics implements Serializable {
    @Expose
    private Category maxCategory;

    @Expose
    private Category maxYearCategory;

    @Expose
    private Category maxMonthCategory;

    @Expose
    private Category maxDayCategory;

    private Map<String, Category> categories;

    public Statistics(Map<String, Category> categories) {
        this.categories = categories;
    }

    public Statistics() {
    }

    public void setMaxMonthCategory(String date) {
        Optional<Category> category = categories.values()
                .stream()
                .max(Comparator.comparingInt(c -> c.getMonthSum(date)));
        category.ifPresent(value -> maxMonthCategory = new Category(value.getCategory(), value.getMonthSum(date)));
    }

    public void setMaxDayCategory(String date) {
        Optional<Category> category = categories.values()
                .stream()
                .max(Comparator.comparingInt(c -> c.getDaySum(date)));
        category.ifPresent(value -> maxDayCategory = new Category(value.getCategory(), value.getDaySum(date)));
    }

    public void setMaxYearCategory(String date) {
        Optional<Category> category = categories.values()
                .stream()
                .max(Comparator.comparingInt(c -> c.getYearSum(date)));
        category.ifPresent(value -> maxYearCategory = new Category(value.getCategory(), value.getYearSum(date)));
    }

    public void setMaxCategory() {
        Optional<Category> category = categories.values()
                .stream()
                .max(Comparator.comparingInt(c -> c.getSum()));
        category.ifPresent(value -> maxCategory = new Category(value.getCategory(), value.getSum()));
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void setCategoriesFromList(List<Category> categoryList) {
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
