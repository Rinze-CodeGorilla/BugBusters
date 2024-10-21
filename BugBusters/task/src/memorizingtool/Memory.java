package memorizingtool;//Chapter 3

import java.util.Scanner;
//With excitement coursing through her veins, Lily decided to embark on a quest to find the lock...

/**
 * This class is all about joining or combining the functionalities of the previously mentioned classes
 * (BooleanMemorize, NumberMemorize, and WordMemorize) into one comprehensive memory management system.
 * <p>
 * With the Memory class, we can now have a unified approach to memory management,
 * allowing us to store and manipulate different types of data seamlessly
 */
class Memory {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    Welcome to Data Memory!
                    Possible actions:
                    1. Memorize booleans
                    2. Memorize numbers
                    3. Memorize words
                    0. Quit""");
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    new BooleanMemorize().Run();
                    break;
                case "2":
                    new NumberMemorize().Run();
                    break;
                case "3":
                    new WordMemorize().Run();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Incorrect command");
                    break;
            }
            System.gc();
        }
    }
}