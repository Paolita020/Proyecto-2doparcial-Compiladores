import java.util.List;
import java.util.Stack;

public class ASA implements Parser {
    private int c = 0;
    private Token preanalisis;
    private final List<Token> tokens;
    private Stack<Integer> pila=new Stack();
    private boolean hayErrores = false;
    private boolean salir=false;
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
                    if (preanalisis.tipo == TipoToken.SELECT) {
                        pila.push(2);
                        c++;
                        preanalisis = tokens.get(c);
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
                    if(preanalisis.tipo==TipoToken.FROM){
                    pila.push(10);
                    c++;
                    preanalisis = tokens.get(c);
                }else hayErrores=true;
                    
                break;
                case 4:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) pila.push(9);
                    else if (preanalisis.tipo == TipoToken.ASTERISCO) pila.push(6);
                    else hayErrores=true;
                    c++;
                    preanalisis = tokens.get(c);
                break;
                case 5://reduccion
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == ) pila.push();
                        else hayErrores = true;
                    }
                    else hayErrores=true;
                break;
                case 6:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == ) pila.push();
                        //posiblemente quites la de abajo
                        else if (pila.peek() == ) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 7:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == ) pila.push();
                    }
                    else hayErrores=true;
                break;
                case 8:
                    if(preanalisis.tipo==TipoToken.COMA) {
                        pila.push(13);
                        c++;
                        preanalisis=tokens.get(c);
                    }else if(preanalisis.tipo==TipoToken.FROM){
                        pila.pop();
                        if (pila.peek() ==  || pila.peek()==) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 9:
                    if(preanalisis.tipo==TipoToken.PUNTO) {
                        pila.push(15);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.FROM){
                        pila.pop();
                        if(pila.peek()==){
                            pila.push();
                        }
                    }
                    else hayErrores=true;
                break;
                case 10:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(18);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 11:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        if(pila.peek()==){
                            pila.push();
                        }
                    }//duda del siguiente condicional
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
                    if(preanalisis.tipo==TipoToken.FROM||preanalisis.tipo==TipoToken.EOF) {
                        pila.pop();
                        if (pila.peek() == ) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 13:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(9);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 14:
                    if(preanalisis.tipo==TipoToken.COMA || preanalisis.tipo==TipoToken.FROM){
                        pila.pop();
                        if(pila.peek()==){
                            pila.push();
                        }else hayErrores=true;
                    }else hayErrores=true;
                    break;
                case 15:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(20);
                        c++;
                        preanalisis=tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 16:
                    if(preanalisis.tipo==TipoToken.EOF) {
                        //pila.pop();
                        pila.pop();
                        if(pila.peek()== ){
                            pila.push();
                        }
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 17:
                    if(preanalisis.tipo==TipoToken.COMA) {
                        pila.push(22);
                        c++;
                        preanalisis=tokens.get(c);
                    }
                    else if(preanalisis.tipo==TipoToken.EOF){
                        pila.pop();
                        if(pila.peek()==){
                            pila.push();
                        }
                        
                    }hayErrores=true;
                break;
                case 18:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR){
                        pila.push(24);
                        c++;
                        preanalisis=tokens.get(c);
                    }else if(preanalisis.tipo==TipoToken.COMA || preanalisis.tipo==TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == ) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 19:
                    if(preanalisis.tipo==TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == ||pila.peek() == ) pila.push();
                        //chance quitas el condicional de abajo
                        else if(pila.peek()==) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 20:
                    if(preanalisis.tipo==TipoToken.COMA || preanalisis.tipo=TipoToken.FROM) {
                        pila.pop();
                        if(pila.peek()==){
                            pila.push();
                        }
                    }
                    else hayErrores=true;
                break;
                case 21:
                    if(preanalisis.tipo==TipoToken.EOF) {
                        //pila.pop();
                        pila.pop();
                        if (pila.peek() == ) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores=true;
                break;
                case 22:
                    if(preanalisis.tipo==TipoToken.IDENTIFICADOR) {
                        pila.push(18);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    else hayErrores=true;
                break;
                case 23:
                    if(preanalisis.tipo==TipoToken.COMA||preanalisis.tipo==TipoToken.EOF) {
                        //pila.pop();
                        //pila.pop();
                        pila.pop();
                        if (pila.peek() == ||pila.peek() == ) pila.push();
                        else hayErrores= true;
                    }
                    else hayErrores= true;
                break;
                case 24:
                    if(preanalisis.tipo==TipoToken.EOF || preanalisis.tipo==TipoToken.COMA){
                        pila.pop():
                        if(pila.peek()==){
                            pila.push();
                        }else hayErrores=true;
                    }else hayErrores=true;
                break;
                case 25:
                    if(preanalisis.tipo==TipoToken.EOF){
                        pila.pop();
                        if(pila.peek()==){
                            pila.push();
                        }else hayErrores=true;
                    }else hayErrores=true;

            }
        }
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
