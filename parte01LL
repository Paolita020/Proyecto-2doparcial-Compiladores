//CODIGO PARA JAVA

//Primer produccióon llamada Q, verifica que la consulta SQL que se manda tenga la palabra inicial "distinct"
//Q -> select D from T

  private void Q() {
        match(TipoToken.SELECT);
        D();
        // match(TipoToken.FROM);
        // T();
    }

//Al igual que la pasada pero ahora con el nombre D, ahora verificamos que la consulta inicie con un "select" y vemos que esta relacacionada con la produccion pasada que es Q 
// D -> distinct P | P

    private void D() {
        if (hayErrores)
            return;

        if (preanalisis.tipo == TipoToken.DISTINCT) {
            match(TipoToken.DISTINCT);
            P();
        } else if (preanalisis.tipo == TipoToken.ASTERISCO
                || preanalisis.tipo == TipoToken.IDENTIFICADOR) {
            P();
        } else {
            hayErrores = true;
            System.out.println("Se esperaba 'distinct' or '*' or 'identificador'");
        }
    }


 //Finalmente tenemos la producción P, que nos ayuda al analisis de las columnas seleccionadas: Como opcion tenemos un asterisco "*" o la separación de columnas por comas.
 // P -> * | A

    private void P() {
        if (hayErrores)
            return;

        if (preanalisis.tipo == TipoToken.ASTERISCO) {
            match(TipoToken.ASTERISCO);
        } else if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
            A();
        } else {
            hayErrores = true;
            System.out.println("Se esperaba '*' or 'identificador'");
        }
    }
