// Crear una lista vacía llamada listaT
List<Object> listaT = new ArrayList<>();

// Agregar elementos a la listaT
listaT.add("T1");
listaT.add("T2");

// Crear un nuevo mapa y agregar la listaT al mapa con la clave TipoToken.IDENTIFICADOR
TablaAS.put("T", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.IDENTIFICADOR, listaT);
}});

// Crear una lista vacía llamada listaT1
List<Object> listaT1 = new ArrayList<>();

// Agregar elementos a la listaT1
listaT1.add("T");
listaT1.add(TipoToken.COMA);

// Agregar la listaT1 al mapa TablaAS con las claves TipoToken.COMA y TipoToken.EOF
TablaAS.put("T1", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.COMA, listaT1);
    put(TipoToken.EOF, listaEpsilon);
}});

// Crear una lista vacía llamada listaT2
List<Object> listaT2 = new ArrayList<>();

// Agregar elementos a la listaT2
listaT2.add("T3");
listaT2.add(TipoToken.IDENTIFICADOR);

// Agregar la listaT2 al mapa TablaAS con la clave TipoToken.IDENTIFICADOR
TablaAS.put("T2", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.IDENTIFICADOR, listaT2);
}});

// Crear una lista vacía llamada listaT3
List<Object> listaT3 = new ArrayList<>();

// Agregar el TipoToken.IDENTIFICADOR a la listaT3
listaT3.add(TipoToken.IDENTIFICADOR);

// Agregar la listaT3 al mapa TablaAS con las claves TipoToken.IDENTIFICADOR, TipoToken.COMA y TipoToken.EOF
TablaAS.put("T3", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.IDENTIFICADOR, listaT3);
    put(TipoToken.COMA, listaEpsilon);
    put(TipoToken.EOF, listaEpsilon);
}});
