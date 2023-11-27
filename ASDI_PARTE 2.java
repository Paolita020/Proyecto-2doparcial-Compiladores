        //A1 -> ,A ! Ɛ
        tablaAS.put("A1", new HashMap<>()); //Se crea se crea una nueva entrada en el mapa tablaAS utilizando el no terminal "A1" como clave.
        tablaAS.get("A1").put(TipoToken.COMA, Arrays.asList(TipoToken.COMA, "A")); 
        /*se obtiene el valor asociado a la clave "A1" en el mapa tablaAS, que es un objeto HashMap.
        Luego, se llama al método put en ese objeto HashMap para agregar una nueva entrada.*/
        tablaAS.get("A1").put(TipoToken.FROM, Arrays.asList());
        tablaAS.get("A1").put(TipoToken.EOF, Arrays.asList());

        //A2 -> idA3
        tablaAS.put("A2", new HashMap<>());//Se crea se crea una nueva entrada en el mapa tablaAS utilizando el no terminal "A2" como clave.
         /*se obtiene el valor asociado a la clave "A2" en el mapa tablaAS, que es un objeto HashMap.
        Luego, se llama al método put en ese objeto HashMap para agregar una nueva entrada.*/
        tablaAS.get("A2").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR, "A3"));

        //A3 -> .id | Ɛ
        tablaAS.put("A3", new HashMap<>());//Se crea se crea una nueva entrada en el mapa tablaAS utilizando el no terminal "A3" como clave.
         /*se obtiene el valor asociado a la clave "A3" en el mapa tablaAS, que es un objeto HashMap.
        Luego, se llama al método put en ese objeto HashMap para agregar una nueva entrada.*/
        tablaAS.get("A3").put(TipoToken.COMA, Arrays.asList(TipoToken.PUNTO, TipoToken.IDENTIFICADOR));
        tablaAS.get("A3").put(TipoToken.FROM, Arrays.asList());
        tablaAS.get("A3").put(TipoToken.COMA, Arrays.asList());
        tablaAS.get("A3").put(TipoToken.EOF, Arrays.asList());
