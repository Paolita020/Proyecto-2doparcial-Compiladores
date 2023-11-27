        /*
        Se crean una entrada en el mapa tablaAS para el no terminal correspondiente 
        y asocian la regla de producción correspondiente a ese no terminal y al terminal TipoToken.
            */

        //T -> T2T1
        tablaAS.put("T", new HashMap<>()); 
        tablaAS.get("T").put(TipoToken.IDENTIFICADOR, Arrays.asList("T2","T1"));

        //T1 -> ,T | Ɛ
        tablaAS.put("T1", new HashMap<>());
        tablaAS.get("T1").put(TipoToken.COMA, Arrays.asList(TipoToken.COMA, "T"));
        tablaAS.get("T1").put(TipoToken.EOF, Arrays.asList());

        //T2 -> idT3
        tablaAS.put("T2", new HashMap<>());
        tablaAS.get("T2").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR, "T3"));
        tablaAS.get("T2").put(TipoToken.EOF, Arrays.asList());

        //T3 -> id | Ɛ
        tablaAS.put("T3", new HashMap<>());
        tablaAS.get("T3").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR));
        tablaAS.get("T3").put(TipoToken.COMA, Arrays.asList());
        tablaAS.get("T3").put(TipoToken.EOF, Arrays.asList());
