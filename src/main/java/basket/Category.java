package basket;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    @Expose
    private String category;
    @Expose
    private int sum;
    private List<Cheque> cheques;

    public Category(String category, int sum) {
        this.category = category;
        this.sum = sum;
        cheques = new ArrayList<>();
    }

    public void addSum(String date, int value) {
        cheques.add(new Cheque(date, value));
        sum += value;
    }

    public int getSum() {
        return this.sum;
    }

    public int getYearSum(String date) {
        int yearSum = 0;

        String year = date.substring(0,4);

        for (Cheque cheque : cheques) {
            String chequeYear = cheque.getDate().substring(0,4);
            if (year.equals(chequeYear)) {
                yearSum += cheque.getSum();
            }
        }
        return yearSum;
    }

    public int getMonthSum(String date) {
        int monthSum = 0;

        String month = date.substring(0,7);

        for (Cheque cheque : cheques) {
            String chequeMonth = cheque.getDate().substring(0,7);
            if (month.equals(chequeMonth)) {
                monthSum += cheque.getSum();
            }
        }
        return monthSum;
    }

    public int getDaySum(String date) {
        int daySum = 0;

        for (Cheque cheque : cheques) {
            if (date.equals(cheque.getDate())) {
                daySum += cheque.getSum();
            }
        }
        return daySum;
    }

    public String getCategory() {
        return category;
    }
}

class Cheque implements Serializable {
    String date;
    int sum;

    public Cheque(String date, int sum) {
        this.date = date;
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }
}