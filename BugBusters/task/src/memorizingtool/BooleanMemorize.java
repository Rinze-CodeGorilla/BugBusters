package memorizingtool;//Chapter 1

import memorizingtool.parser.Parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;

/**
 * It is all about memorizing Booleans. You see, regular Booleans are so forgetful!
 * They constantly change their value, and it's just too much for us to handle.
 * It probably has a magical power to store Boolean values indefinitely. You can pass a Boolean to it, and it will remember it forever.
 * <p>
 * This class must be a lifesaver for forgetful programmers like me. No more worrying about Booleans changing unexpectedly.
 * We can now rely on the trustworthy BooleanMemorize class to keep our Booleans intact. I can't wait to use it in my next project!
 */
public class BooleanMemorize {
    ArrayList<Boolean> list = new ArrayList<>();
    Parser p = new Parser(list);
    boolean finished = false;

    void Run() {
        Scanner scanner = new Scanner(System.in);
        while (!finished) {
            System.out.println("Perform action:");
            String[] data = scanner.nextLine().split(" ");
            IntFunction<String> d = i -> { try { return data[i]; } catch (Exception e) { throw new DataIndexOutOfBoundsException();}};
            try {
                switch (d.apply(0)) {
                    case "/help" -> help();
                    case "/menu" -> menu();
                    case "/add" -> add(p.parseBoolean(d.apply(1)));
                    case "/remove" -> remove(p.parseIndex(d.apply(1)));
                    case "/replace" -> replace(p.parseIndex(d.apply(1)), p.parseBoolean(d.apply(2)));
                    case "/replaceAll" -> replaceAll(p.parseBoolean(d.apply(1)), p.parseBoolean(d.apply(2)));
                    case "/index" -> index(p.parseBoolean(d.apply(1)));
                    case "/sort" -> sort(p.parseSortDirection(d.apply(1)));
                    case "/frequency" -> frequency();
                    case "/print" -> print(p.parseIndex(d.apply(1)));
                    case "/printAll" -> printAll(p.parsePrintType(d.apply(1)));
                    case "/getRandom" -> getRandom();
                    case "/count" -> count(p.parseBoolean(d.apply(1)));
                    case "/size" -> size();
                    case "/equals" -> equals(p.parseIndex(d.apply(1)), p.parseIndex(d.apply(2)));
                    case "/readFile" -> readFile(p.parseString(d.apply(1)));
                    case "/writeFile" -> writeFile(p.parseString(d.apply(1)));
                    case "/clear" -> clear();
                    case "/compare" -> compare(p.parseInt(d.apply(1)), p.parseIndex(d.apply(2)));
                    case "/mirror" -> mirror();
                    case "/unique" -> unique();
                    case "/flip" -> flip(p.parseInt(d.apply(1)));
                    case "/negateAll" -> negateAll();
                    case "/and" -> and(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/or" -> or(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/logShift" -> logShift(p.parseInt(d.apply(1)));
                    case "/convertTo" -> convertTo(p.parseConvertToType(d.apply(1)));
                    case "/morse" -> morse();
                    case "/addRandom" -> addRandom(p.parseInt(d.apply(1)));
                    default -> System.out.println("No such command");
                }
            } catch (DataIndexOutOfBoundsException e) {
                System.out.println("Incorrect amount of arguments");
            } catch (NumberFormatException e) {
                System.out.println("Some arguments can't be parsed!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("File not found!");
            }
        }
    }

    void help() {//a curious young girl named Lily. Lily had a heart full of...
        System.out.println(
                """
                        ===================================================================================================================
                        Usage: COMMAND [<TYPE> PARAMETERS]
                        ===================================================================================================================
                        General commands:
                        ===================================================================================================================
                        /help - Display this help message
                        /menu - Return to the menu
                        
                        /add [<T> ELEMENT] - Add the specified element to the list
                        /remove [<int> INDEX] - Remove the element at the specified index from the list
                        /replace [<int> INDEX] [<T> ELEMENT] - Replace the element at specified index with the new one
                        /replaceAll [<T> OLD] [<T> NEW] - Replace all occurrences of specified element with the new one
                        
                        /index [<T> ELEMENT] - Get the index of the first specified element in the list
                        /sort [ascending/descending] - Sort the list in ascending or descending order
                        /frequency - The frequency count of each element in the list
                        /print [<int> INDEX] - Print the element at the specified index in the list
                        /printAll [asList/lineByLine/oneLine] - Print all elements in the list in specified format
                        /getRandom - Get a random element from the list
                        /count [<T> ELEMENT] - Count the number of occurrences of the specified element in the list
                        /size - Get the number of elements in the list
                        /equals [<int> INDEX1] [<int> INDEX2] - Check if two elements are equal
                        /clear - Remove all elements from the list
                        /compare [<int> INDEX1] [<int> INDEX2] Compare elements at the specified indices in the list
                        /mirror - Mirror elements' positions in list
                        /unique - Unique elements in the list
                        /readFile [<string> FILENAME] - Import data from the specified file and add it to the list
                        /writeFile [<string> FILENAME] - Export the list data to the specified file""");
        System.out.println(
                """
                        ===================================================================================================================
                        Boolean-specific commands:
                        ===================================================================================================================
                        /flip [<int> INDEX] - Flip the specified boolean
                        /negateAll - Negate all the booleans in memory
                        /and [<int> INDEX1] [<int> INDEX2] - Calculate the bitwise AND of the two specified elements
                        /or [<int> INDEX1] [<int> INDEX2] - Calculate the bitwise OR of the two specified elements
                        /logShift [<int> NUM] - Perform a logical shift of elements in memory by the specified amount
                        /convertTo [string/number] - Convert the boolean(bit) sequence in memory to the specified type
                        /morse - Convert the boolean(bit) sequence to Morse code
                        ===================================================================================================================""");
    }

    void menu() {
        this.finished = true;
    }

    void add(Boolean element) {
        list.add(element);
        System.out.println("Element  " + element + "  added");
    }

    void remove(int index) {
        list.remove(index);
        System.out.println("Element on " + index + " position removed");
    }

    void replace(int index, Boolean element) {
        list.set(index, element);
        System.out.println("Element on " + index + " position replaced with " + element);
    }

    //adventure and a mind hungry for knowledge. Every day, she would wander through the...
    void replaceAll(Boolean from, Boolean to) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(from)) {
                list.set(i, to);
            }
        }
        System.out.println("Each " + from + " element replaced with " + to);
    }

    void index(Boolean value) {
        int index = list.indexOf(value);
        if (index < 0) throw new IllegalArgumentException("There is no such element");
        System.out.println("First occurrence of " + value + " is on " + index + " position");
    }

    void sort(String way) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i) && !list.get(j) && way.equals("ascending") || !list.get(i) && list.get(j) && way.equals("descending")) {
                    Boolean temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.printf("Memory sorted %s\n", way);
    }

    void frequency() {
        if (list.isEmpty()) throw new IllegalArgumentException("There are no elements memorized");
        Map<Boolean, Long> counts = new HashMap<>();
        for (Boolean b : list) {
            if (counts.get(b) == null) {
                counts.put(b, 1L);
            } else {
                counts.put(b, counts.get(b) + 1);
            }
        }

        System.out.println("Frequency:");
        for (Map.Entry<Boolean, Long> entry : counts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    void print(int index) {
        System.out.println("Element on " + index + " position is " + list.get(index));
    }

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
                for (Boolean i : list) {
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

    //village, observing the world around her and asking questions that often left the villagers perplexed...
    void count(Boolean value) {
        int amount = 0;
        for (Boolean i : list) {
            if (i == value) {
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
        FileReaderBoolean readerThread = new FileReaderBoolean();
        ArrayList<Boolean> list2 = readerThread.read(path);
        for (Boolean i : list2) {
            list.add(i);
        }
        System.out.println("Data imported: " + (list2.size()));
    }

    void writeFile(String path) throws IOException {
        FileWriterBoolean writer = new FileWriterBoolean();
        writer.write(path, list);
        System.out.println("Data exported: " + list.size());
    }

    void clear() {
        list.clear();
        System.out.println("Data cleared");
    }

    void compare(int i, int j) {
        if (list.get(i) && !list.get(j)) {
            System.out.println("Result: " + list.get(i) + " > " + list.get(j));
        } else if (!list.get(i) && list.get(j)) {
            System.out.println("Result: " + list.get(i) + " < " + list.get(j));
        } else {
            System.out.println("Result: " + list.get(i) + " = " + list.get(j));
        }
    }

    void mirror() {
        ArrayList<Boolean> list2 = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            list2.add(list.get(i));
        }
        list = list2;
        System.out.println("Data reversed");
    }

    void unique() {
        Map<Boolean, Long> counts = new HashMap<>();
        for (Boolean i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }
        ArrayList<Boolean> list2 = new ArrayList<>();
        for (Map.Entry<Boolean, Long> entry : counts.entrySet()) {
            list2.add(entry.getKey());
        }
        System.out.println("Unique values: " + Arrays.toString(list2.toArray()));
    }

    void flip(int index) {
        list.set(index, !list.get(index));
        System.out.println("Element on " + index + " position flipped");
    }

    void negateAll() {
        list.replaceAll(e -> !e);
        System.out.println("All elements negated");
    }

    void and(int i, int j) {
        boolean a = list.get(i), b = list.get(j);
        boolean res = a && b;
        System.out.printf("Operation performed: (%b && %b) is %b\n", a, b, res);
    }

    void or(int i, int j) {
        boolean a = list.get(i), b = list.get(j);
        boolean res = a || b;
        System.out.printf("Operation performed: (%b || %b) is %b\n", a, b, res);
    }

    void logShift(int n) {
        int outputValue = n;
        int size = list.size();

        if (size == 0) {
            return;
        }
        n %= size;
        if (n < 0) {
            n += size;
        }
        for (int i = 0; i < n; i++) {
            Boolean last = list.get(size - 1);
            for (int j = size - 1; j > 0; j--) {
                list.set(j, list.get(j - 1));
            }
            list.set(0, last);
        }
        System.out.println("Elements shifted by " + outputValue);
    }

    void convertTo(String type) {
        if (list.isEmpty()) throw new IllegalArgumentException("No data memorized");
        StringBuilder binary = new StringBuilder();
        for (boolean b : list) {
            if (b) {
                binary.append("1");
            } else {
                binary.append("0");
            }
        }
        switch (type.toLowerCase()) {
            case "number":
                System.out.println("Converted: " + Long.parseLong(binary.toString(), 2));
                break;
            case "string":
                int byteSize = Byte.SIZE;
                StringBuilder sb = new StringBuilder();
                if (binary.length() % byteSize != 0) {
                    System.out.println("Amount of elements is not divisible by 8, so the last " + binary.length() % byteSize + " of " + "them will be ignored");
                }
                for (int i = 0; i < binary.length(); i += byteSize) {
                    String segment = binary.substring(i, Math.min(i + byteSize, binary.length()));
                    int asciiValue = Integer.parseInt(segment, 2);
                    sb.append((char) asciiValue);
                }
                String asciiSequence = sb.toString();
                System.out.println("Converted: " + asciiSequence);
                break;
        }
    }

    void morse() {
        if (list.isEmpty()) throw new IllegalArgumentException("No data memorized");
        StringBuilder morseCode = new StringBuilder("Morse code: ");
        for (boolean b : list) {
            if (b) {
                morseCode.append(".");
            } else {
                morseCode.append("_");
            }
        }
        System.out.println(morseCode);
    }

    private void addRandom(int i) {
        Random r = new Random();
        for (int j = 0; j < i; j++) {
            list.add(r.nextBoolean());
        }
    }
}
