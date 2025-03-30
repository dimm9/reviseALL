package bookDecorator;

import java.io.IOException;

public class LimitedEditionDecorator extends BookDecorator{
    public LimitedEditionDecorator(Book book) {
        super(book);
    }

    @Override
    public String description() {
        return book.description() + "limited edition," ;
    }

    @Override
    public double calculateCost() {
        return book.calculateCost() + 25;
    }

    @Override
    public void writeToFile(String file) throws IOException {
        book.writeToFile(file);
    }
}
