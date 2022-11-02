package server;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public LocalDateTime getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return LocalDateTime.parse(this.date, formatter);
    }

    public int getSum() {
        return sum;
    }
}
