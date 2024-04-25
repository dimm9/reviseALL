import bookDecorator.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) {
        Poetry poetry = Poetry.getInstance(Path.of("books.csv"));
        poetry.getDescription().entrySet().forEach(pair -> System.out.println(pair.getValue()));
        List<Author> usaAuthors = Poetry.filterUS(poetry);
        Optional<Author> a1 = usaAuthors.stream().max(Comparator.comparing(a -> a.getBirthYear()));
        Author a2 = usaAuthors.stream().max(Comparator.comparing(a -> ((Author)a).getBirthYear()).reversed()).orElse(null);
        System.out.println(a1.toString() + " " + a2);
        List<Author> usaMapped = usaAuthors.stream()
                .peek(a -> {
                    if(a.getBirthYear() > 1900){
                        a.setLastName(a.getLastName()+" [modern]");
                    }
                })
                .toList();
        usaMapped.forEach(a -> System.out.println(a.toString()));

        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("John", 30);
        originalMap.put("Alice", 25);
        originalMap.put("Bob", 40);

        // Transforming the keys to uppercase
        //The entrySet() method in Java returns a set view of the mappings contained in the map.
        Map<String, Integer> transformedMap = originalMap.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().toUpperCase(), // Key transformation
                        entry -> entry.getValue() // Value remains unchanged
                ));
        System.out.println(transformedMap);

        List<String> names = Arrays.asList("Alice", "Bob", "Anna", "David", "Andrew");
        Set<String> namesStartingWithA = names.stream()
                        .filter(name -> name.startsWith("A"))
                                .collect(Collectors.toSet());
        System.out.println("Names starting with 'A': " + namesStartingWithA);
        Book book = new HardCoverDecorator(new LimitedEditionDecorator(new Romance("Dark Tower", LocalDate.ofYearDay(1987, 2), 657)));
        System.out.println(book.description() + book.calculateCost());

    }
}