package Server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request {
    private String title;
    private String date;
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
