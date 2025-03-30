package bookDecorator;

import java.io.IOException;

public interface Book {
    public String description();
    public double calculateCost();

    public static Book parseDescription() {
        return null;
    }

    public void writeToFile(String file) throws IOException;
}
