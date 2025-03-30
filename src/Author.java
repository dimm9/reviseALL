import java.io.*;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Author implements Serializable {
    public int getBirthYear() {
        return birthYear;
    }
    private String name;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    private String lastName;
    private int birthYear;

    public Author(String name, String lastName, int birthYear) {
        this.name = name;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }
    public static Author parseLine(String name, String dateStr){
        String[] nameAndLastName = name.split(" ");
        int date = Stream.of(dateStr)
                .map(s -> s.replaceAll("\\D", "")) //remove all non-digit characters("\D) from the string
                .mapToInt(s ->Integer.parseInt(s))
                .findFirst()
                .orElse(-1);
        return new Author(nameAndLastName[0], nameAndLastName[1], date);
    }
    public static void toBinFile(Path path, Author a) {
        try {
            FileOutputStream fileOut = new FileOutputStream(path.toFile());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(a);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static Author fromBinFile(Path path) {
        try {
            FileInputStream fileIn = new FileInputStream(path.toFile());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Author a = (Author) in.readObject();
            fileIn.close();
            in.close();
            return a;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public String toString() {
        return name + " " + lastName + " " + birthYear;
    }
}
