package memorizingtool;//Chapter 5

import memorizingtool.parser.Parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.IntFunction;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Continuing with our theme of memorization, this class is designed to help us remember words or strings.
 * After all, words can be quite elusive, especially when working with large amounts of text.
 * The WordMemorize class provides methods to store and recall words.
 * <p>
 * This class goes a step further to offer additional functionalities specific to words.
 * It has methods like
 * "concatenate" to join multiple words together,
 * "length" to determine the length of a word
 * "reverse" to reverse the order of characters in a word.
 * <p>
 * With the WordMemorize class in our toolkit, we can confidently keep track of important words and manipulate them as needed.
 */
public class WordMemorize {
    ArrayList<String> list = new ArrayList<>();
    boolean finished = false;
    Parser p = new Parser(list);

    //a satisfying click, the heavy doors slowly creaked open, revealing a dazzling...
    void Run() {
        Scanner scanner = new Scanner(System.in);
        while (!finished) {
            System.out.println("Perform action:");
            String[] data = scanner.nextLine().split(" ");
            IntFunction<String> d = i -> {
                try {
                    return data[i];
                } catch (Exception e) {
                    throw new DataIndexOutOfBoundsException();
                }
            };
            try {
                switch (d.apply(0)) {
                    case "/help" -> help();
                    case "/menu" -> menu();
                    case "/add" -> add(p.parseString(d.apply(1)));
                    case "/remove" -> remove(p.parseIndex(d.apply(1)));
                    case "/replace" -> replace(p.parseInt(d.apply(1)), p.parseString(d.apply(2)));
                    case "/replaceAll" -> replaceAll(p.parseString(d.apply(1)), p.parseString(d.apply(2)));
                    case "/index" -> index(p.parseString(d.apply(1)));
                    case "/sort" -> sort(p.parseSortDirection(d.apply(1)));
                    case "/frequency" -> frequency();
                    case "/print" -> print(p.parseInt(d.apply(1)));
                    case "/printAll" -> printAll(p.parsePrintType(d.apply(1)));
                    case "/getRandom" -> getRandom();
                    case "/count" -> count(p.parseString(d.apply(1)));
                    case "/size" -> size();
                    case "/equals" -> equals(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/readFile" -> readFile(p.parseString(d.apply(1)));
                    case "/writeFile" -> writeFile(p.parseString(d.apply(1)));
                    case "/clear" -> clear();
                    case "/compare" -> compare(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/mirror" -> mirror();
                    case "/unique" -> unique();
                    case "/concat" -> concat(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/swapCase" -> swapCase(p.parseInt(d.apply(1)));
                    case "/upper" -> upper(p.parseInt(d.apply(1)));
                    case "/lower" -> lower(p.parseInt(d.apply(1)));
                    case "/reverse" -> reverse(p.parseInt(d.apply(1)));
                    case "/length" -> length(p.parseInt(d.apply(1)));
                    case "/join" -> join(p.parseString(d.apply(1)));
                    case "/regex" -> regex(p.parseString(d.apply(1)));
                    case "/addRandom" -> addRandom(p.parseInt(d.apply(1)));
                    default -> System.out.println("No such command");
                }
            } catch (DataIndexOutOfBoundsException e) {
                System.out.println("Incorrect amount of arguments");
            } catch (NumberFormatException e) {
                System.out.println("Some arguments can't be parsed!");
            } catch (PatternSyntaxException e) {
                System.out.println("Incorrect regex pattern provided");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds!");
            } catch (IOException e) {
                System.out.println("File not found!");
            }
        }
    }

    private void addRandom(int i) {
        Random r = new Random();
        for (int j = 0; j < i; j++) {
            var b= new byte[7];
            r.nextBytes(b);
            list.add(new String(b, StandardCharsets.US_ASCII));
        }
    }

    void help() {
        System.out.println("===================================================================================================================\n" + "Usage: COMMAND [<TYPE> PARAMETERS]\n" + "===================================================================================================================\n" + "General commands:\n" + "===================================================================================================================\n" + "/help - Display this help message\n" + "/menu - Return to the menu\n" + "\n" + "/add [<T> ELEMENT] - Add the specified element to the list\n" + "/remove [<int> INDEX] - Remove the element at the specified index from the list\n" + "/replace [<int> INDEX] [<T> ELEMENT] - Replace the element at specified index with the new one\n" + "/replaceAll [<T> OLD] [<T> NEW] - Replace all occurrences of specified element with the new " + "one\n" + "\n" + "/index [<T> ELEMENT] - Get the index of the first specified element in the list\n" + "/sort [ascending/descending] - Sort the list in ascending or descending order\n" + "/frequency - The frequency count of each element in the list\n" + "/print [<int> INDEX] - Print the element at the specified index in the list\n" + "/printAll [asList/lineByLine/oneLine] - Print all elements in the list in specified format\n" + "/getRandom - Get a random element from the list\n" + "/count [<T> ELEMENT] - Count the number of occurrences of the specified element in the list\n" + "/size - Get the number of elements in the list\n" + "/equals [<int> INDEX1] [<int> INDEX2] - Check if two elements are equal\n" + "/clear - Remove all elements from the list\n" + "/compare [<int> INDEX1] [<int> INDEX2] Compare elements at the specified indices in the list\n" + "/mirror - Mirror elements' positions in list\n" + "/unique - Unique elements in the list\n" + "/readFile [<string> FILENAME] - Import data from the specified file and add it to the list\n" + "/writeFile [<string> FILENAME] - Export the list data to the specified file");
        System.out.println("===================================================================================================================\n" + "Word-specific commands:\n" + "===================================================================================================================\n" + "/concat [<int> INDEX1] [<int> INDEX2] Concatenate two specified strings\n" + "/swapCase [<int> INDEX] Output swapped case version of the specified string\n" + "/upper [<int> INDEX] Output uppercase version of the specified string\n" + "/lower [<int> INDEX] Output lowercase version of the specified string\n" + "/reverse [<int> INDEX] Output reversed version of the specified string\n" + "/length [<int> INDEX] Get the length of the specified string\n" + "/join [<string> DELIMITER] Join all the strings with the specified delimiter\n" + "/regex [<string> PATTERN] Search for all elements that match the specified regular expression " + "pattern\n" + "===================================================================================================================");
    }

    void menu() {
        this.finished = true;
    }

    void add(String element) {
        list.add(element);
        System.out.println("Element " + element + " added");
    }

    //chamber filled with sparkling jewels and ancient artifacts.
    void remove(int index) {
        list.remove(index);
        System.out.println("Element on " + index + " position removed");
    }

    void replace(int index, String element) {
        list.set(index, element);
        System.out.println("Element on " + index + " position replaced with " + element);
    }

    void replaceAll(String from, String to) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(from)) {
                list.set(i, to);
            }
        }
        System.out.println("Each " + from + " element replaced with " + to);
    }

    void index(String value) {
        int index = list.indexOf(value);
        if (index < 0) throw new IllegalArgumentException("There is no such element");
        System.out.println("First occurrence of " + value + " is on " + index + " position");
    }

    void sort(String way) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i).compareTo(list.get(j)) > 0 && way.equals("ascending") || list.get(i).compareTo(list.get(j)) < 0 && way.equals("descending")) {
                    String temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.printf("Memory sorted %s\n", way);
    }

    //And so, Lily's unwavering curiosity and determination led her to a treasure...
    void frequency() {
        if (list.isEmpty()) throw new IllegalArgumentException("There are no elements memorized");
        Map<String, Long> counts = new HashMap<>();
        for (String i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }

        System.out.println("Frequency:");
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    void print(int index) {
        System.out.println("Element on " + index + " position is " + list.get(index));
    }

    //trove of knowledge and beauty. From that day forward, she became known as the village's greatest...
    void getRandom() {
        if (list.isEmpty()) throw new IllegalArgumentException("There are no elements memorized");
        Random random = new Random();
        System.out.println("Random element: " + list.get(random.nextInt(list.size())));
    }

    void printAll(String type) {
        switch (type) {
            case "asList":
                System.out.println("List of elements:\n" + Arrays.toString(list.toArray()));
                break;
            case "lineByLine":
                System.out.println("List of elements:\n");
                for (String i : list) {
                    System.out.println(i);
                }
                break;
            case "oneLine":
                System.out.println("List of elements:");
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.print(list.get(i) + " ");
                }
                if (list.size() > 0) System.out.print(list.get(list.size() - 1));
                System.out.println();
                break;
        }
    }

    void count(String value) {
        int amount = 0;
        for (String i : list) {
            if (i.equals(value)) {
                amount++;
            }
        }
        System.out.println("Amount of " + value + ": " + amount);
    }

    void size() {
        System.out.println("Amount of elements: " + list.size());
    }

    void equals(int i, int j) {
        boolean res = list.get(i).equals(list.get(j));
        System.out.printf("%d and %d elements are%s equal: %s\n", i, j, res ? "" : " not", list.get(i) + (res ? " = " : " != ") + list.get(j));
    }

    void readFile(String path) throws IOException {
        FileReaderWords readerThread = new FileReaderWords();
        ArrayList<String> list2 = readerThread.read(path);
        for (String i : list2) {
            list.add(i);
        }
        System.out.println("Data imported: " + (list2.size()));
    }

    void writeFile(String path) throws IOException {
        FileWriterWords writer = new FileWriterWords();
        writer.write(path, list);
        System.out.println("Data exported: " + list.size());
    }

    void clear() {
        list.clear();
        System.out.println("Data cleared");
    }

    void compare(int i, int j) {
        if (list.get(i).compareTo(list.get(j)) > 0) {
            System.out.println("Result: " + list.get(i) + " > " + list.get(j));
        } else if (list.get(i).compareTo(list.get(j)) < 0) {
            System.out.println("Result: " + list.get(i) + " < " + list.get(j));
        } else {
            System.out.println("Result: " + list.get(i) + " = " + list.get(j));
        }
    }

    void mirror() {
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            list2.add(list.get(i));
        }
        list = list2;
        System.out.println("Data reversed");
    }

    void unique() {
        Map<String, Long> counts = new HashMap<>();
        for (String i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }
        ArrayList<String> list2 = new ArrayList<>();
        for (Map.Entry<String, Long> entry : counts.entrySet()) {
            list2.add(entry.getKey());
        }
        System.out.println("Unique values: " + Arrays.toString(list2.toArray()));
    }

    //explorer, sharing her discoveries and inspiring others to pursue their own adventures.
    void concat(int i, int j) {
        System.out.println("Concatenated string: " + list.get(i) + list.get(j));
    }

    void swapCase(int i) {
        System.out.printf("\"%s\" string with swapped case: ", list.get(i));
        for (char c : (list.get(i)).toCharArray()) {
            if (Character.isUpperCase(c)) {
                System.out.print(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                System.out.print(Character.toUpperCase(c));
            } else {
                System.out.print(c);
            }
        }
        System.out.println();
    }

    void upper(int i) {
        System.out.printf("Uppercase \"%s\" string: %s\n", list.get(i), (list.get(i)).toUpperCase());
    }

    void lower(int i) {
        System.out.printf("Lowercase \"%s\" string: %s\n", list.get(i), (list.get(i)).toLowerCase());
    }

    void reverse(int i) {
        System.out.printf("Reversed \"%s\" string: %s\n", list.get(i), new StringBuilder(list.get(i)).reverse());
    }

    void length(int i) {
        System.out.printf("Length of \"%s\" string: %d\n", list.get(i), (list.get(i)).length());
    }

    void join(String delimiter) {
        System.out.printf("Joined string: %s\n", String.join(delimiter, list));
    }

    void regex(String regex) {
        List<String> matchingElements = new ArrayList<>();
        Pattern pattern;
        pattern = Pattern.compile(regex);
        for (String element : list) {
            if (pattern.matcher(element).matches()) {
                matchingElements.add(element);
            }
        }
        if (matchingElements.isEmpty()) throw new IllegalArgumentException("There are no strings that match provided regex");
        System.out.println("Strings that match provided regex:");
        System.out.println(Arrays.toString(matchingElements.toArray()));
    }
}
