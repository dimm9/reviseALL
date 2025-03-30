package bookDecorator;

import java.time.LocalDate;

public class Romance extends BasicBook{
    public static final String type = "ROMANCE";
    public Romance(String title, LocalDate date, int pages) {
        super(title, date, pages);
    }

}
