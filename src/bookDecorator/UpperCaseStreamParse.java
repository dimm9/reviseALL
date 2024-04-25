package bookDecorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UpperCaseStreamParse extends FileInputStream {
    public UpperCaseStreamParse(File file) throws FileNotFoundException {
        super(file);
    }
    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c != -1) {
            return Character.toUpperCase((char) c);
        }
        return c;
    }
}
