// Creamos una lista de objetos para almacenar los elementos de la lista A
List<Object> listaA = new ArrayList<>();
listaA.add("A1");
listaA.add("A2");

// Creamos un mapa para almacenar la información de la tabla AS
// La clave es "A"
TablaAS.put("A", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.IDENTIFICADOR, listaA);
}});

// Creamos una lista de objetos para almacenar los elementos de la lista A2
List<Object> listaA2 = new ArrayList<>();
listaA2.add("A3");
listaA2.add(TipoToken.IDENTIFICADOR);

// Añadimos la lista A2 al mapa TablaAS con clave "A2" 
TablaAS.put("A2", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.IDENTIFICADOR, listaA2);
}});

// Creamos una lista de objetos para almacenar los elementos de la lista A3
List<Object> listaA3 = new ArrayList<>();
listaA3.add(TipoToken.IDENTIFICADOR);
listaA3.add(TipoToken.PUNTO);

// Añadimos la lista A3 al mapa TablaAS con clave "A3" 
TablaAS.put("A3", new HashMap<TipoToken, List<Object>>() {{
    put(TipoToken.FROM, listaEpsilon);
    put(TipoToken.COMA, listaEpsilon);
    put(TipoToken.PUNTO, listaA3);
}});
