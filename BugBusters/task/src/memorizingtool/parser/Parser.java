package memorizingtool.parser;

import java.util.ArrayList;

public class Parser {
    ArrayList list;
    public Parser(ArrayList list) {
        this.list = list;
    }
    public Boolean parseBoolean(String input) {
        if ("true".equalsIgnoreCase(input)) return true;
        if ("false".equalsIgnoreCase(input)) return false;
        throw new NumberFormatException();
    }
    public int parseInt(String input) {
            return Integer.parseInt(input);
    }
    public double parseDouble(String input) {
            return Double.parseDouble(input);
    }
    public String parseSortDirection(String input) {
        return switch (input) {
            case "ascending" -> "ascending";
            case "descending" -> "descending";
            default -> throw new IllegalArgumentException("Incorrect argument, possible arguments: ascending, descending");
        };
    }
    public String parsePrintType(String input) {
        return switch (input) {
            case "asList" -> "asList";
            case "lineByLine" -> "lineByLine";
            case "oneLine" -> "oneLine";
            default -> throw new IllegalArgumentException("Incorrect argument, possible arguments: asList, lineByLine, oneLine");
        };
    }
    public String parseConvertToType(String input) {
        return switch (input) {
            case "string" -> "string";
            case "number" -> "number";
            default -> throw new IllegalArgumentException("Incorrect argument, possible arguments: string, number");
        };
    }
    public int parsePositive(String input) {
        int result = parseInt(input);
        if (result < 0) throw new IllegalArgumentException("undefined");
        return result;
    }

    public int parseIndex(String input) {
        int index = parseInt(input);
        if (index < 0) throw new IllegalArgumentException("Index out of bounds!");
        if (index >= list.size()) throw new IllegalArgumentException("Index out of bounds!");
        return index;
    }

    public String parseString(String input) {
        return input;
    }
}
