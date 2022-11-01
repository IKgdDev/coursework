package basket;

public class Category {
    private String category;
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
