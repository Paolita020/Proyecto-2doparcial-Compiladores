    // T -> T2 T1
    private void T() {
        if (hayErrores)
            return;

        T2();
        T1();
    }

    // T2 -> id T3
    private void T2() {
        if (hayErrores)
            return;

        if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
            match(TipoToken.IDENTIFICADOR);
            T3();
        } else {
            hayErrores = true;
            System.out.println("Se esperaba un 'identificador'");
        }
    }

    // T1 -> ,T | Ɛ
    private void T1() {
        if (hayErrores)
            return;

        if (preanalisis.tipo == TipoToken.COMA) {
            match(TipoToken.COMA);
            T();
        }
    }

    // T3 -> id | Ɛ
    private void T3() {
        if (hayErrores)
            return;

        if (preanalisis.tipo == TipoToken.IDENTIFICADOR) {
            match(TipoToken.IDENTIFICADOR);
        } else {
            hayErrores = true;
            System.out.println("Se esperaba un 'identificador'");
        }
    }
