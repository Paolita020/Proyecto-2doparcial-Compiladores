import java.util.*;

public class AscendingParser {
    private Stack<String> stack;
    private List<String> input;


    /*Esta es la función del constructor de la clase AscendingParser.
    Recibe una lista de String como entrada, que representa los tokens de la cadena a analizar.
    Inicializa la pila (stack) con el símbolo inicial Q y agrega el símbolo de fin de cadena $.*/

    public AscendingParser(List<String> input) {
        this.input = input;
        stack = new Stack<>();
        stack.push("$");
        stack.push("Q");
    }
/*
Utiliza un bucle mientras la pila no esté vacía. En cada iteración, verifica si el símbolo en la cima de la pila es un terminal o no terminal. 
Si es un terminal, compara con el token actual de la entrada y realiza las acciones correspondientes.
Si es un no terminal, llama a la función parseProduction() para expandir el no terminal.
*/
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
//Esta función lleva a cabo la expansión de un no terminal según las reglas de producción de la gramática. 
    private boolean parseProduction(String nonTerminal, String currentToken) {
        switch (nonTerminal) {

            /* 
            Q → select D from T. 
            Verifica si el token actual es "select" 
            Realiza las siguientes acciones: elimina "Q" de la pila, y luego agrega en orden los símbolos "T", "from", "D" y "select" a la pila. 
            */
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
            /*
            D → distinct P | P. 
            Verifica si el token actual es "distinct". 
            Realiza las siguientes acciones: elimina "D" de la pila y agrega "P" y "distinct" a la pila. 
            Si el token actual no es "distinct", se llama a parseProduction("P", currentToken) para continuar con la expansión de la producción D → P.
            */
            case "D":
                if (currentToken.equals("distinct")) {
                    stack.pop();
                    stack.push("P");
                    stack.push("distinct");
                    return true;
                } else {
                    return parseProduction("P", currentToken);
                }

            /*
            P → * | A. Primero
            Verifica si el token actual es "". 
            Realiza las siguientes acciones: elimina "P" de la pila y agrega "" a la pila. 
            Si el token actual no es "*", se llama a parseProduction("A", currentToken) para continuar con la expansión de la producción P → A.
            */
            case "P":
                if (currentToken.equals("*")) {
                    stack.pop();
                    stack.push("*");
                    return true;
                } else {
                    return parseProduction("A", currentToken);
                }

            /* 
            A → A , A1 | A1. 
            Verifica si el token actual es "id". 
            Realiza las siguientes acciones: elimina "A" de la pila y agrega "A1" a la pila. 
            Si el token actual no es "id", retorna false indicando que la producción no se pudo aplicar.
            */
            case "A":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("A1");
                    return true;
                }
                return false;

                
            /*   
           A1 → id A2. 
           Verifica si el token actual es "id". 
           Realiza las siguientes acciones: elimina "A1" de la pila y agrega "A2" y "id" en ese orden a la pila.
           Si el token actual no es "id", retorna false indicando que la producción no se pudo aplicar.
            */
            case "A1":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("A2");
                    stack.push("id");
                    return true;
                }
                return false;

            /*
            A2 → . id | Ɛ. 
            Primero, verifica si el token actual es ".". 
            Realiza las siguientes acciones: elimina "A2" de la pila y agrega "id" y "." en ese orden a la pila. 
            Si el token actual no es ".", simplemente elimina "A2" de la pila, lo que refleja la expansión de la producción A2 → Ɛ (épsilon).
            */

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

            /*   
            T → T , T1 | T1. 
            Verifica si el token actual es "id". 
            Realiza las siguientes acciones: elimina "T" de la pila y agrega "T1" a la pila. 
            Si el token actual no es "id", retorna false indicando que la producción no se pudo aplicar.
            */
            case "T":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("T1");
                    return true;
                }
                return false;

            /*   
            T1 → id T2. 
            Verifica si el token actual es "id". 
            Realiza las siguientes acciones: elimina "T1" de la pila y agrega "T2" y "id" en ese orden a la pila. 
            Si el token actual no es "id", retorna false indicando que la producción no se pudo aplicar.
            */
            case "T1":
                if (currentToken.equals("id")) {
                    stack.pop();
                    stack.push("T2");
                    stack.push("id");
                    return true;
                }
                return false;

            /*
            Este caso maneja la producción T2 → id | Ɛ. 
            Verifica si el token actual es "id". 
            Realiza las siguientes acciones: elimina "T2" de la pila y agrega "id" a la pila.
            Si el token actual no es "id", simplemente elimina "T2" de la pila, lo que refleja la expansión de la producción
            */
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

    /*
    Esta función verifica si un símbolo es terminal o no. 
    Comprueba si el primer carácter del símbolo es una letra mayúscula. 
    Si es una letra mayúscula, se considera no terminal; de lo contrario, se considera terminal.
    */
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
