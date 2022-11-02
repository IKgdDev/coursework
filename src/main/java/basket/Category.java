package basket;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Category implements Serializable {
    @Expose
    private String category;
    @Expose
    private int sum;

    public Category(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public void addSum(int value){
        sum += value;
    }

    public int getSum(){
        return this.sum;
    }

    public String getCategory() {
        return category;
    }
}
