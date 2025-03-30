package bookDecorator;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicBook implements Book {
    public String getTitle() {
        return title;
    }
    protected String title;
    protected LocalDate date;
    protected int pages;
    protected List<BasicBook> sameAuthor;


    public BasicBook(String title, LocalDate date, int pages) {
        this.title = title;
        this.date = date;
        this.pages = pages;
        this.sameAuthor = new ArrayList<>();
    }
    public void addSame(BasicBook book){
        sameAuthor.add(book);
    }

    @Override
    public String description() {
        String f = this.title + "," + this.date.toString() + "," + this.pages + ",";
        for(BasicBook b : sameAuthor){
            f += b.getTitle() + ",";
        }
        return f;
    }

    @Override
    public double calculateCost() {
        return 20;
    }

    @Override
    public void writeToFile(String file) {
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(file, true))) {
            bf.write(description() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static BasicBook parseDescription(String desc) {
        String[] parts = desc.split(",", -1);
        String name = parts[0];
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(parts[1], form);
        int pages = Integer.parseInt(parts[2]);
        return new BasicBook(name, date, pages);
    }
    public static List<Book> readFromFile(String file) throws FileNotFoundException {
        List<Book> books = new ArrayList<>();
        Map<String, BookAndBooks> similar = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine())!=null){
                BookAndBooks bookWith = BookAndBooks.parseDescription(line);
                BasicBook b = bookWith.getBook();
                books.add(b);
                similar.put(b.getTitle(), bookWith);
            }
            BookAndBooks.connectBooks(similar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
    public static void makeCheck(List<Book> books){
        double cost = 0;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("check.txt"))) {
            for(Book b : books){
                bw.write(b.description() + "\n");
                cost += b.calculateCost();
            }
            bw.write("Cost: " + String.valueOf(cost));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;

    }

}
