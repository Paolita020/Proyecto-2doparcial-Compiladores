//ASDI ANALISIS DE LA TABLA HECHA


        //Q -> select D from T
        tablaAS.put("Q", new HashMap<>());
        tablaAS.get("Q").put(TipoToken.SELECT, Arrays.asList(TipoToken.SELECT, "D", TipoToken.FROM, "T"));

        //D -> distinct P | P
        tablaAS.put("D", new HashMap<>());
        tablaAS.get("D").put(TipoToken.DISTINCT, Arrays.asList(TipoToken.DISTINCT, "P"));
        tablaAS.get("D").put(TipoToken.ASTERISCO, Arrays.asList("P"));
        tablaAS.get("D").put(TipoToken.IDENTIFICADOR, Arrays.asList("P"));

        //P -> * | A
        tablaAS.put("P", new HashMap<>());
        tablaAS.get("P").put(TipoToken.ASTERISCO, Arrays.asList(TipoToken.ASTERISCO));
        tablaAS.get("P").put(TipoToken.IDENTIFICADOR, Arrays.asList("A"));

        //A -> A2A1 
        tablaAS.put("A", new HashMap<>());
        tablaAS.get("A").put(TipoToken.IDENTIFICADOR, Arrays.asList("A2", "A1"));

        //A1 -> ,A ! Ɛ
        tablaAS.put("A1", new HashMap<>());
        tablaAS.get("A1").put(TipoToken.COMA, Arrays.asList(TipoToken.COMA, "A"));
        tablaAS.get("A1").put(TipoToken.FROM, Arrays.asList());
        tablaAS.get("A1").put(TipoToken.EOF, Arrays.asList());

        //A2 -> idA3
        tablaAS.put("A2", new HashMap<>());
        tablaAS.get("A2").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR, "A3"));

        //A3 -> .id | Ɛ
        tablaAS.put("A3", new HashMap<>());
        tablaAS.get("A3").put(TipoToken.COMA, Arrays.asList(TipoToken.PUNTO, TipoToken.IDENTIFICADOR));
        tablaAS.get("A3").put(TipoToken.FROM, Arrays.asList());
        tablaAS.get("A3").put(TipoToken.COMA, Arrays.asList());
        tablaAS.get("A3").put(TipoToken.EOF, Arrays.asList());

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
