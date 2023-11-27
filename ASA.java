import java.util.List;
import java.util.Stack;

public class ASA implements Parser {
    //declaramos las variables a utilizar
    private int c = 0;
    private Token preanalisis;
    private final List<Token> tokens;
    private Stack<Integer> pila=new Stack();
    private boolean hayErrores = false;
    private boolean salir=false;
    
    //Toma una lista de tokens como argumento y asignarla a la variable tokens
    public ASA(List<Token> tokens) {
        this.tokens = tokens;
        preanalisis=this.tokens.get(c);
        this.pila.push(0);
    }
    @Override
    public boolean parse() {
        do {
            switch (pila.peek()) {
                case 0:
                    //verifica si el tipo de token actual es tipo select
                    if (preanalisis.tipo == TipoToken.SELECT) {
                        pila.push(2);// empuja el valor de la pila en 2
                        c++;
                        preanalisis = tokens.get(c);// actualiza el valor con el siguiente token en la lista
                    }
                    else hayErrores=true;
                break;
                case 1:
                    if (preanalisis.tipo == TipoToken.EOF) salir= true;
                    break;
                case 2:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(9);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if (preanalisis.tipo == TipoToken.ASTERISCO) {
                        pila.push(6);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if (preanalisis.tipo == TipoToken.DISTINCT) {
                        pila.push(4);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 3:
                    if(preanalisis.tipo==TipoToken.FROM) pila.push(10);
                    else hayErrores=true;
                    c++;
                    preanalisis = tokens.get(c);
                break;
                case 4:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) pila.push(9);
                    else if (preanalisis.tipo == TipoToken.ASTERISCO) pila.push(6);
                    else hayErrores=true;
                    c++;
                    preanalisis = tokens.get(c);
                break;
                case 5:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == 2) pila.push(3);
                        else hayErrores = true;
                    }
                    else hayErrores=true;
                break;
                case 6:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == 2) pila.push(5);
                        else if (pila.peek() == 4) pila.push(18);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 7:
                    if(preanalisis.tipo==TipoToken.COMA) {
                        pila.push(22);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if(preanalisis.tipo==TipoToken.FROM){
                        pila.pop();
                        if (pila.peek() == 2) pila.push(5);
                        else if (pila.peek() == 4) pila.push(18);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 8:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == 2 || pila.peek()==4) pila.push(7);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 9:
                    if(preanalisis.tipo==TipoToken.PUNTO) {
                        pila.push(20);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.FROM) pila.push(19);
                    else hayErrores=true;
                break;
                case 10:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(13);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 11:
                    if(preanalisis.tipo==TipoToken.COMA) {
                        pila.push(14);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if(preanalisis.tipo==TipoToken.EOF){
                        pila.pop();
                        pila.pop();
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 0) pila.push(1);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 12:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.EOF) {
                        pila.pop();
                        if (pila.peek() == 10) pila.push(11);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 13:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(17);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.EOF) pila.push(16);
                    else hayErrores=true;
                break;
                case 14:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(13);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                case 15:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 10) pila.push(11);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 16:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 10) pila.push(12);
                        else if(pila.peek() == 14) pila.push(15);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 17:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.EOF) {
                        pila.pop();
                        if (pila.peek() == 13) pila.push(16);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 18:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 2) pila.push(3);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 19:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 2||pila.peek() == 4) pila.push(8);
                        else if(pila.peek()==22) pila.push(23);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 20:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(21);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 21:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 9) pila.push(19);
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 22:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(9);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 23:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 2||pila.peek() == 4) pila.push(7);
                        else hayErrores= true;
                    }
                    else hayErrores= true;
                break;
            }
        }
        // verifica si hay errores
        while (!hayErrores && !salir);
        if(hayErrores){
            System.out.println("Consulta invalida");
            return false;
        }
        else{
            System.out.println("Consulta correcta");
            return true;
        }
    }
}

