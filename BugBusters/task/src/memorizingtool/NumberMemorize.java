package memorizingtool;//Chapter 4

import memorizingtool.parser.Parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.function.IntFunction;

/**
 * Ah, the class NumberMemorize! Well, if we're following the same line of thinking, it is all about helping us remember numbers.
 * Because, let's be honest, numbers can be quite slippery and elusive sometimes.
 * <p>
 * But that's not all! It has some additional features tailored specifically for numbers like:
 * "increment" to increase the stored value by a certain amount,
 * "decrement" to decrease it, and maybe even "multiply" and "divide" to perform basic arithmetic operations.
 * <p>
 * With NumberMemorize at our disposal, we won't have to worry about forgetting or losing track of important numerical values.
 * It's like having a virtual assistant dedicated solely to keeping our numbers safe and accessible.
 */

public class NumberMemorize {
    ArrayList<Integer> list = new ArrayList<>();
    boolean finished = false;
    Parser p = new Parser(list);

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
                    case "/add" -> add(p.parseInt(d.apply(1)));
                    case "/remove" -> remove(p.parseIndex(d.apply(1)));
                    case "/replace" -> replace(p.parseIndex(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/replaceAll" -> replaceAll(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/index" -> index(p.parseInt(d.apply(1)));
                    case "/sort" -> sort(p.parseSortDirection(d.apply(1)));
                    case "/frequency" -> frequency();
                    case "/print" -> print(p.parseIndex(d.apply(1)));
                    case "/printAll" -> printAll(p.parsePrintType(d.apply(1)));
                    case "/getRandom" -> getRandom();
                    case "/count" -> count(p.parseInt(d.apply(1)));
                    case "/size" -> size();
                    case "/equals" -> equals(p.parseIndex(d.apply(1)), p.parseIndex(d.apply(2)));
                    case "/readFile" -> readFile(p.parseString(d.apply(1)));
                    case "/writeFile" -> writeFile(p.parseString(d.apply(1)));
                    case "/clear" -> clear();
                    case "/compare" -> compare(p.parseInt(d.apply(1)), p.parseIndex(d.apply(2)));
                    case "/mirror" -> mirror();
                    case "/unique" -> unique();
                    case "/sum" -> sum(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/subtract" -> subtract(p.parseIndex(d.apply(1)), p.parseIndex(d.apply(2)));
                    case "/multiply" -> multiply(p.parseIndex(d.apply(1)), p.parseIndex(d.apply(2)));
                    case "/divide" -> divide(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/pow" -> pow(p.parseInt(d.apply(1)), p.parseInt(d.apply(2)));
                    case "/factorial" -> factorial(p.parseInt(d.apply(1)));
                    case "/sumAll" -> sumAll();
                    case "/average" -> average();
                    case "/addRandom" -> addRandom(p.parseInt(d.apply(1)));
                    default -> System.out.println("No such command");
                }
            } catch (DataIndexOutOfBoundsException e) {
                System.out.println("Incorrect amount of arguments");
            } catch (NumberFormatException e) {
                //print something extra to prevent infinite loop test failing correct code
                System.out.println("%s: Some arguments can't be parsed!".formatted(d.apply(0)));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of bounds!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("File not found!");
            }
        }
    }

    //for any mention of a hidden treasure or a forgotten secret that might hold the key to her discovery.
    void help() {
        System.out.println(
                "===================================================================================================================\n" +
                        "Usage: COMMAND [<TYPE> PARAMETERS]\n" +
                        "===================================================================================================================\n" +
                        "General commands:\n" +
                        "===================================================================================================================\n" +
                        "/help - Display this help message\n" +
                        "/menu - Return to the menu\n" +
                        "\n" +
                        "/add [<T> ELEMENT] - Add the specified element to the list\n" +
                        "/remove [<int> INDEX] - Remove the element at the specified index from the list\n" +
                        "/replace [<int> INDEX] [<T> ELEMENT] - Replace the element at specified index with the new one\n" +
                        "/replaceAll [<T> OLD] [<T> NEW] - Replace all occurrences of specified element with the new " + "one\n" +
                        "\n" +
                        "/index [<T> ELEMENT] - Get the index of the first specified element in the list\n" +
                        "/sort [ascending/descending] - Sort the list in ascending or descending order\n" +
                        "/frequency - The frequency count of each element in the list\n" +
                        "/print [<int> INDEX] - Print the element at the specified index in the list\n" +
                        "/printAll [asList/lineByLine/oneLine] - Print all elements in the list in specified format\n" +
                        "/getRandom - Get a random element from the list\n" +
                        "/count [<T> ELEMENT] - Count the number of occurrences of the specified element in the list\n" +
                        "/size - Get the number of elements in the list\n" +
                        "/equals [<int> INDEX1] [<int> INDEX2] - Check if two elements are equal\n" +
                        "/clear - Remove all elements from the list\n" +
                        "/compare [<int> INDEX1] [<int> INDEX2] Compare elements at the specified indices in the list\n" +
                        "/mirror - Mirror elements' positions in list\n" +
                        "/unique - Unique elements in the list\n" +
                        "/readFile [<string> FILENAME] - Import data from the specified file and add it to the list\n" +
                        "/writeFile [<string> FILENAME] - Export the list data to the specified file"
        );
        System.out.println(
                "===================================================================================================================\n" +
                        "Number-specific commands:\n" +
                        "===================================================================================================================\n" +
                        "/sum [<int> INDEX1] [<int> INDEX2] - Calculate the sum of the two specified elements\n" +
                        "/subtract [<int> INDEX1] [<int> INDEX2] - Calculate the difference between the two specified " + "elements\n" +
                        "/multiply [<int> INDEX1] [<int> INDEX2] - Calculate the product of the two specified elements\n" +
                        "/divide [<int> INDEX1] [<int> INDEX2] - Calculate the division of the two specified elements\n" +
                        "/pow [<int> INDEX1] [<int> INDEX2] - Calculate the power of the specified element to the " + "specified exponent element\n" +
                        "/factorial [<int> INDEX] - Calculate the factorial of the specified element\n" +
                        "/sumAll - Calculate the sum of all elements\n" +
                        "/average - Calculate the average of all elements\n" +
                        "==================================================================================================================="
        );
    }

    //One evening, while deep in her research, Lily stumbled upon an ancient map hidden...
    void menu() {
        this.finished = true;
    }

    void add(int element) {
        list.add(element);
        System.out.println("Element " + element + " added");
    }

    void remove(int index) {
        list.remove(index);
        System.out.println("Element on " + index + " position removed");
    }

    void replace(int index, int element) {
        list.set(index, element);
        System.out.println("Element on " + index + " position replaced with " + element);
    }

    void replaceAll(int from, int to) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(from)) {
                list.set(i, to);
            }
        }
        System.out.println("Each " + from + " element replaced with " + to);
    }

    //within the pages of an old book. The map depicted a hidden cave at the summit of the tallest hill, rumored...
    void index(int value) {
        int index = list.indexOf(value);
        if (index < 0) throw new IllegalArgumentException("There is no such element");
        System.out.println("First occurrence of " + value + " is on " + index + " position");
    }

    void sort(String way) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (list.get(i) > list.get(j) && way.equals("ascending") || list.get(i) < list.get(j) && way.equals("descending")) {
                    int temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        System.out.printf("Memory sorted %s\n", way);
    }

    void frequency() {
        if (list.isEmpty()) throw new IllegalArgumentException("There are no elements memorized");
        Map<Integer, Long> counts = new HashMap<>();
        for (int i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }

        System.out.println("Frequency:");
        for (Map.Entry<Integer, Long> entry : counts.entrySet()) {
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
    }//to hold the key to unlocking unimaginable wonders. The key in Lily's...

    void printAll(String type) {
        switch (type) {
            case "asList":
                System.out.println("List of elements:\n" +
                        Arrays.toString(list.toArray()));
                break;
            case "lineByLine":
                System.out.println("List of elements:\n");
                for (int i : list) {
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

    void count(int value) {
        int amount = 0;
        for (int i : list) {
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
        FileReaderInteger readerThread = new FileReaderInteger();
        ArrayList<Integer> list2 = readerThread.read(path);
        for (int i : list2) {
            list.add(i);
        }
        System.out.println("Data imported: " + (list2.size()));
    }

    void writeFile(String path) throws IOException {
        FileWriterInteger writer = new FileWriterInteger();
        writer.write(path, list);
        System.out.println("Data exported: " + list.size());
    }

    void clear() {
        list.clear();
        System.out.println("Data cleared");
    }

    //possession seemed to match the one shown on the map.
    void compare(int i, int j) {

        if (list.get(i) > list.get(j)) {

            System.out.println("Result: " + list.get(i) + " > " + list.get(j));

        } else if (list.get(i) < list.get(j)) {

            System.out.println("Result: " + list.get(i) + " < " + list.get(j));

        } else {

            System.out.println("Result: " + list.get(i) + " = " + list.get(j));

        }
    }

    //With the map as her guide, Lily set out on an arduous journey up the treacherous hill, navigating through...
    void mirror() {

        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = list.size() - 1; i >= 0; i--) {

            list2.add(list.get(i));

        }
        list = list2;
        System.out.println("Data reversed");

    }

    void unique() {
        Map<Integer, Long> counts = new HashMap<>();
        for (int i : list) {
            if (counts.get(i) == null) {
                counts.put(i, 1L);
            } else {
                counts.put(i, counts.get(i) + 1);
            }
        }
        ArrayList<Integer> list2 = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : counts.entrySet()) {
            list2.add(entry.getKey());
        }
        System.out.println("Unique values: " + Arrays.toString(list2.toArray()));
    }

    //dense forests and rocky terrain. After days of perseverance, she finally reached the summit and stood before...
    void sum(int i, int j) {
        long a = list.get(i), b = list.get(j);
        long res = a + b;
        System.out.printf("Calculation performed: %d + %d = %d\n", a, b, res);
    }

    void subtract(int i, int j) {
        long a = list.get(i), b = list.get(j);
        long res = a - b;
        System.out.printf("Calculation performed: %d - %d = %d\n", a, b, res);
    }

    void multiply(int i, int j) {
        long a = list.get(i), b = list.get(j);
        long res = a * b;
        System.out.printf("Calculation performed: %d * %d = %d\n", a, b, res);
    }

    void divide(int i, int j) {
        long a = list.get(i), b = list.get(j);
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        double res = a * 1.0 / b;
        System.out.printf("Calculation performed: %d / %d = %f\n", a, b, res);
    }

    void pow(int i, int j) {
        long a = list.get(i), b = list.get(j);
        BigDecimal res = BigDecimal.valueOf(a).pow((int) b, MathContext.DECIMAL128);
//        long res = Math.pow(a, b);
        System.out.printf("Calculation performed: %d ^ %d = %f\n", a, b, res);
    }

    void factorial(int index) {
        int fac = list.get(index);
        if (fac < 0) throw new IllegalArgumentException("undefined");
        long res = 1;
        int i = 1;
        do {
            res = res * (i++);
        } while (i <= fac);
        System.out.printf("Calculation performed: %d! = %d\n", fac, res);
    }

    //the entrance of the hidden cave. With a deep breath, she inserted the silver key into the lock, and with...
    void sumAll() {
        long sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println("Sum of all elements: " + sum);
    }

    void average() {
        double sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println("Average of all elements: %f".formatted(sum / list.size()));
    }
    private void addRandom(int i) {
        Random r = new Random();
        for (int j = 0; j < i; j++) {
            list.add(r.nextInt());
        }
    }
}
