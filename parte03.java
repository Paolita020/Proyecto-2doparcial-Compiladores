
/*
 * “parseT()”: Esta función representa la producción T en la gramática. 
 * Analiza la parte de la consulta SQL que se refiere a la tabla principal de la consulta (“parseT2()” y “parseT1()”)
 */
private static void parseT() {
    if (position == input.length()) {
        return;
    }

    parseT2();
    parseT1();
}

/*
 * “parseT2()”: Esta función representa la producción T2 en la gramática. 
 * Analiza el identificador de la tabla en la consulta SQL, en caso de haberlo (“parseT3()”).
 */
private static void parseT2() {
    if (position == input.length()) {
        return;
    }
    if (Character.isLetter(input.charAt(position)) || Character.isDigit(input.charAt(position))) {
        position++;
    }
    parseT3();
}

/*
 *  “parseT1()”: Esta función representa la producción T1 en la gramática.
 *  Analiza las columnas de las tablas en la consulta SQL que tengan una coma(,) 
 * seguido de un análisis de una tabla seleccionada(“parseT()”).
 */
private static void parseT1() {
    if (position == input.length()) {
        return;
    }
    
    if (input.startsWith(",", position)) {
        position++;
        parseT();
    }
}

/*
 * “parseT3()”: Esta función representa la producción T3 en la gramática. 
 * Analiza el identificador de la tabla en la consulta SQL, en caso de haberlo.
 */
private static void parseT3() {
    if (position == input.length()) {
        return;
    }
    
    if (Character.isLetter(input.charAt(position)) || Character.isDigit(input.charAt(position))) {
        position++;
    }

}
