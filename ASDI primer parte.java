HashMap<String, HashMap<TipoToken, List<Object>>> TablaAS = new HashMap<>();
        List<Object> listaQSelect = new ArrayList<>();
        listaQSelect.add("T");
        listaQSelect.add(TipoToken.FROM);
        listaQSelect.add("D");
        listaQSelect.add(TipoToken.SELECT);
      //Función que agrega la producción en este caso Q a la tabla de analisis para el token SELECT
        TablaAS.put("Q", new HashMap<TipoToken,List<Object>>(){{
            put(TipoToken.SELECT, listaQSelect);
        }});
//Tabla que analizará la entrada del lenguaje 

//las siguientes funciones, hablando de los "list" representan la gramatica para el no terminal x, cuando el token que se espera es de los tipos IDENTIFICADO, ASTERISCO, SELECT O DISTINCT
List<Object> listaPId = new ArrayList<>();
        listaPId.add("A");
        List<Object> listaPAsterisco = new ArrayList<>();
        listaPAsterisco.add(TipoToken.ASTERISCO);
      //Función que agrega la producción en este caso P a la tabla de analisis para los tokens IDENTIFICADOR Y ASTERISCO
        TablaAS.put("P", new HashMap<TipoToken,List<Object>>(){{
            put(TipoToken.IDENTIFICADOR, listaPId);
            put(TipoToken.ASTERISCO, listaPAsterisco);
        }});
List<Object> listaD = new ArrayList<>();
        listaD.add("P");
       //Función que agrega la producción en este caso D a la tabla de analisis para los tokens DISTINCT, IDENTIFICADOR Y ASTERISCO
        TablaAS.put("D", new HashMap<TipoToken,List<Object>>(){{
            put(TipoToken.DISTINCT, listaD);
            put(TipoToken.IDENTIFICADOR, listaD);
            put(TipoToken.ASTERISCO, listaD);
        }});

//Producción que indica la presencia de un epsilon en la sentencia
List<Object> listaEpsilon = new ArrayList<>();
        List<Object> listaA1 = new ArrayList<>();
      //Las siguientes 2 lineas nos agregan, elementos de tipo a1 a la lista y nos indican que se puede esperar una coma despues del elemento, respectivamente
        listaA1.add("A");
        listaA1.add(TipoToken.COMA);
       //Funcion que agrega la producción en este caso A1 a la tabla de analisis para los tokens COMA Y FROM
        TablaAS.put("A1", new HashMap<TipoToken,List<Object>>(){{
            put(TipoToken.COMA, listaA1);
            put(TipoToken.FROM, listaEpsilon);
        }});
