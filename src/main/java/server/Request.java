package server;

import com.google.gson.annotations.Expose;

public class Request {
    @Expose
    private String title;
    @Expose
    private String date;
    @Expose
    private int sum;

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }
}
