//Tabla que analizará la entrada del lenguaje 
    private Map<String, Map<TipoToken, List<Object >>> tablaAS;

    public TAnalisis(){
        tablaAS = new HashMap<>();

        /*
        Se crea un nuevo objeto HashMap vacío que se asigna como el valor del no terminal correspondiente como clave.
        Se obtiene el valor asociado a la clave en el mapa tablaAS, que es un objeto HashMap. 
        Luego, se llama al método put en ese objeto HashMap para agregar una nueva entrada.
        */

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
