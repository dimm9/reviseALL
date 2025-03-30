package bookDecorator;

import java.io.IOException;

public class HardCoverDecorator extends BookDecorator{

    public HardCoverDecorator(Book book) {
        super(book);
    }

    @Override
    public String description() {
        return book.description() + "hard cover,";
    }

    @Override
    public double calculateCost() {
        return book.calculateCost() + 15;
    }

    @Override
    public void writeToFile(String file) throws IOException {
        book.writeToFile(file);
    }
}
