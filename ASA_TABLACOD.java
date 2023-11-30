//TABLA A CODIGO  
@Override
    public boolean parse() {
        do {
            switch (pila.peek()) {
                case 0:
                    if (preanalisis.tipo == TipoToken.SELECT) { // d2
                        pila.push(2);
                        c++;
                        preanalisis = tokens.get(c);
                    } else
                        hayErrores = true;
                    break;
                case 1:
                    if (preanalisis.tipo == TipoToken.EOF)
                        salir = true;
                    break;
                case 2:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(9);
                        c++;
                        preanalisis = tokens.get(c);
                    } else if (preanalisis.tipo == TipoToken.ASTERISCO) {
                        pila.push(6);
                        c++;
                        preanalisis = tokens.get(c);
                    } else if (preanalisis.tipo == TipoToken.DISTINCT) {
                        pila.push(4);
                        c++;
                        preanalisis = tokens.get(c);
                    } else
                        hayErrores = true;
                    break;
                case 3:
                    if (preanalisis.tipo == TipoToken.FROM)
                        pila.push(10);
                    else
                        hayErrores = true;
                    c++;
                    preanalisis = tokens.get(c);
                    break;
                case 4:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(9);
                        c++;
                        preanalisis = tokens.get(c);
                    } else if (preanalisis.tipo == TipoToken.ASTERISCO) {
                        pila.push(6);
                        c++;
                        preanalisis = tokens.get(c);
                    } else
                        hayErrores = true;

                    break;

                case 5: // reduccionr2 D,1
                    if (preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == 2) {
                            pila.push(3);
                        } else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 6:// reduccion3 P,1
                    if (preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == 2)
                            pila.push(5);
                        else if (pila.peek() == 4)
                            pila.push(11);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 7:// reduccionr4 P,1
                    if (preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        if (pila.peek() == 2)
                            pila.push(5);
                        else if (pila.peek() == 4)
                            pila.push(11);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 8:
                    if (preanalisis.tipo == TipoToken.COMA) {
                        pila.push(13);
                        c++;
                        preanalisis = tokens.get(c);
                    } else if (preanalisis.tipo == TipoToken.FROM) {
                        // reduccion r7 A1,0
                        // pila.pop();
                        if (pila.peek() == 8)
                            pila.push(12);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 9:
                    if (preanalisis.tipo == TipoToken.PUNTO) {
                        pila.push(15);
                        c++;
                        preanalisis = tokens.get(c);
                    } // cambiar reduccion (r10) A3,0 //pila.pop();
                    else if (preanalisis.tipo == TipoToken.COMA || preanalisis.tipo == TipoToken.FROM) {
                        if (pila.peek() == 9)
                            pila.push(14);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 10:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(18);
                        c++;
                        preanalisis = tokens.get(c);
                    } else
                        hayErrores = true;
                    break;
                case 11:// Reduccion r1 D,2
                    if (preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 2) {
                            pila.push(3);
                        } else {
                            hayErrores = true;
                        }
                    } else
                        hayErrores = true;
                    break;
                case 12: // reduccion r5, A,2
                    if (preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 2 || pila.peek() == 4)
                            pila.push(7);
                        else if (pila.peek() == 13)
                            pila.push(19);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 13:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(9);
                        c++;
                        preanalisis = tokens.get(c);
                    }
                    break;
                case 14:// REDUCCION r8 A2,2
                    if (preanalisis.tipo == TipoToken.COMA || preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 2 || pila.peek() == 4 || pila.peek() == 13)
                            pila.push(8);
                    } else
                        hayErrores = true;
                case 15:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(20);
                        c++;
                        preanalisis = tokens.get(c);
                    } else
                        hayErrores = true;
                    break;
                case 16:// reduccion r0 Q,4
                    if (preanalisis.tipo == TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 0)
                            pila.push(1);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 17:
                    if (preanalisis.tipo == TipoToken.COMA) {
                        pila.push(22);
                        c++;
                        preanalisis = tokens.get(c);
                    } else if (preanalisis.tipo == TipoToken.EOF) {
                        // Reduccion r13 t1,0
                        // pila.pop();
                        if (pila.peek() == 17)
                            pila.push(21);
                        else
                            hayErrores = true;

                    } else
                        hayErrores = true;

                    break;
                case 18:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(24);
                        c++;
                        preanalisis = tokens.get(c);
                    } else if (preanalisis.tipo == TipoToken.COMA || preanalisis.tipo == TipoToken.EOF) { // reduc 16
                                                                                                          // t3,0
                        // pila.pop();
                        if (pila.peek() == 18)
                            pila.push(23);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 19:// ajustar la reduccion r6 a1,2
                    if (preanalisis.tipo == TipoToken.FROM) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 8)
                            pila.push(12);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 20:// cambiar a reduccion r9 a3,2
                    if (preanalisis.tipo == TipoToken.FROM || preanalisis.tipo == TipoToken.COMA) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 9)
                            pila.push(14);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 21:// ajustar la reduccion r11 t,2
                    if (preanalisis.tipo == TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 10)
                            pila.push(16);
                        else if (pila.peek() == 22)
                            pila.push(25);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 22:
                    if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
                        pila.push(18);
                        c++;
                        preanalisis = tokens.get(c);
                    } else
                        hayErrores = true;
                    break;
                case 23:// ajustar la reduccion r14 t2,2
                    if (preanalisis.tipo == TipoToken.COMA || preanalisis.tipo == TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 10 || pila.peek() == 22)
                            pila.push(17);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 24:// reduccion r15 t3,1
                    if (preanalisis.tipo == TipoToken.COMA || preanalisis.tipo == TipoToken.EOF) {
                        pila.pop();
                        if (pila.peek() == 18)
                            pila.push(23);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
                case 25:// reduccion r12 t1,2
                    if (preanalisis.tipo == TipoToken.EOF) {
                        pila.pop();
                        pila.pop();
                        if (pila.peek() == 17)
                            pila.push(21);
                        else
                            hayErrores = true;
                    } else
                        hayErrores = true;
                    break;
            }
        } while (!hayErrores && !salir);
        if (hayErrores) {
            System.out.println("Sentencia incorrecta");
            return false;
        } else {
            System.out.println("Sentencia correcta");
            return true;
        }
    }
}
