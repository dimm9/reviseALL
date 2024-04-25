package bookDecorator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Horror extends BasicBook{
    private final static String type = "HORROR";
    public Horror(String title, LocalDate date, int pages) {
        super(title, date, pages);
    }
    public static Horror parseDescription(String desc) {
        String[] parts = desc.split(",", -1);
        String name = parts[0];
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy");
        LocalDate date = LocalDate.parse( parts[2], form);
        int pages = Integer.getInteger(parts[1]);
        return new Horror(name, date, pages);
    }
}
