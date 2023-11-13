import java.util.*;

public class AscendingParser {
    private Stack<String> stack;
    private List<String> input;

    public AscendingParser(List<String> input) {
        this.input = input;
        stack = new Stack<>();
        stack.push("$");
        stack.push("Q");
    }

    public boolean parse() {
        int i = 0;
        String currentToken = input.get(i);

        while (!stack.isEmpty()) {
            String top = stack.peek();

            if (isTerminal(top)) {
                if (top.equals(currentToken)) {
                    stack.pop();
                    i++;
                    if (i < input.size()) {
                        currentToken = input.get(i);
                    }
                } else {
                    return false;
                }
            } else {
                if (!parseProduction(top, currentToken)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean parseProduction(String nonTerminal, String currentToken) {
        switch (nonTerminal) {
            case "Q":
                if (currentToken.equals("select")) {
                    stack.pop();
                    stack.push("T");
                    stack.push("from");
                    stack.push("D");
                    stack.push("select");
                    return true;
                }
                return false;

            case "D":
                if (currentToken.equals("distinct")) {
                    stack.pop();
                    stack.push("P");
                    stack.push("distinct");
                    return true;
                } else {
                    return parseProduction("P", currentToken);
                }

            case "P":
                if (currentToken.equals("*")) {
                    stack.pop();
                    stack.push("*");
                    return true;
                } else {
                    return parseProduction("A", currentToken);
                }

            case "A":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("A1");
                    return true;
                }
                return false;

            case "A1":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("A2");
                    stack.push("id");
                    return true;
                }
                return false;

            case "A2":
                if (currentToken.equals(".")) {
                    stack.pop();
                    stack.push("id");
                    stack.push(".");
                    return true;
                } else {
                    stack.pop();
                    return true;
                }

            case "T":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("T1");
                    return true;
                }
                return false;

            case "T1":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("T2");
                    stack.push("id");
                    return true;
                }
                return false;

            case "T2":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("id");
                    return true;
                } else {
                    stack.pop();
                    return true;
                }

            default:
                return false;
        }
    }

    private boolean isTerminal(String symbol) {
        return !Character.isUpperCase(symbol.charAt(0));
    }

    public static void main(String[] args) {
        List<String> input = Arrays.asList("select", "distinct", "*", "from", "T");
        AscendingParser parser = new AscendingParser(input);

        if (parser.parse()) {
            System.out.println("La cadena es válida.");
        } else {
            System.out.println("La cadena no es válida.");
        }
    }
}
