package bookDecorator;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    public static void main(String[] args) throws IOException {
        Book book1 = new Horror("Dark tower", LocalDate.of(1999, 5, 12), 635);
        book1 = new HardCoverDecorator(book1);
        Book book2 = new LimitedEditionDecorator(new Romance("Idiot", LocalDate.ofYearDay(1869, 1), 574));
        Book book3 = new LimitedEditionDecorator(new Horror("Homeless", LocalDate.ofYearDay(1979, 5), 324));
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        BasicBook.makeCheck(books);
        for(Book b : books){
            System.out.println(b.description());
        }


        List<Book> books2 = BasicBook.readFromFile("book.txt");
        for(Book b : books2){
            System.out.println(b.description());
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("books.bin"));
            out.writeObject("The Dark King");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            InputStream input = new UpperCaseStreamParse(new File("books.bin"));
            int character;
            while ((character = input.read()) != -1) {
                System.out.print((char) character);
            }
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
