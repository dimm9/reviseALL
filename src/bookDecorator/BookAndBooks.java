package bookDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookAndBooks{
    public BasicBook getBook() {
        return book;
    }

    BasicBook book;
    List<String> bookTitles;

    public BookAndBooks(BasicBook book, List<String> bookTitles) {
        this.book = book;
        this.bookTitles = bookTitles;
    }

    public static BookAndBooks parseDescription(String line){
        String[] parts = line.split(",", -1);
        BasicBook book = BasicBook.parseDescription(line);
        List<String> bookTitles = new ArrayList<>();
        for(int i=3; i<parts.length; i++){
            bookTitles.add(parts[i]);
        }
        return new BookAndBooks(book, bookTitles);
    }
    public static void connectBooks(Map<String, BookAndBooks> map){
        for(BookAndBooks similarBook : map.values()){
            BasicBook baseBook = similarBook.getBook();
            for(String bookSimilarTitle : similarBook.bookTitles){
                if(map.containsKey(bookSimilarTitle)){
                    BookAndBooks connectedBook = map.get(bookSimilarTitle);
                    baseBook.addSame(connectedBook.getBook());
                }
            }
        }

    }
}
