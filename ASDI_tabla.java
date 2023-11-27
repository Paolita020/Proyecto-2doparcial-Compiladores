//ASDI ANALISIS DE LA TABLA HECHA
import java.util.*;

public class TablaG {
    // Tabla que analizará la entrada del lenguaje
    private Map<String, Map<TipoToken, List<Object>>> tablaAS;

    public TablaG() {
        tablaAS = new HashMap<>();

        /*
         * Se crea un nuevo objeto HashMap vacío que se asigna como el valor del no
         * terminal correspondiente como clave.
         * Se obtiene el valor asociado a la clave en el mapa tablaAS, que es un objeto
         * HashMap.
         * Luego, se llama al método put en ese objeto HashMap para agregar una nueva
         * entrada.
         */

        // Q -> select D from T
        tablaAS.put("Q", new HashMap<>());
        tablaAS.get("Q").put(TipoToken.SELECT, Arrays.asList(TipoToken.SELECT, "D", TipoToken.FROM, "T"));

        // D -> distinct P | P
        tablaAS.put("D", new HashMap<>());
        tablaAS.get("D").put(TipoToken.DISTINCT, Arrays.asList(TipoToken.DISTINCT, "P"));
        tablaAS.get("D").put(TipoToken.ASTERISCO, Arrays.asList("P"));
        tablaAS.get("D").put(TipoToken.IDENTIFICADOR, Arrays.asList("P"));

        // P -> * | A
        tablaAS.put("P", new HashMap<>());
        tablaAS.get("P").put(TipoToken.ASTERISCO, Arrays.asList(TipoToken.ASTERISCO));
        tablaAS.get("P").put(TipoToken.IDENTIFICADOR, Arrays.asList("A"));

        // A -> A2A1
        tablaAS.put("A", new HashMap<>());
        tablaAS.get("A").put(TipoToken.IDENTIFICADOR, Arrays.asList("A2", "A1"));

        // A1 -> ,A | (epsilon)
        tablaAS.put("A1", new HashMap<>());
        /*
         * Se crea se crea una nueva entrada en el mapa tablaAS utilizando el no
         * terminal "A1" como clave.
         * tablaAS.get("A1").put(TipoToken.COMA, Arrays.asList(TipoToken.COMA, "A"));
         * /*
         * se obtiene el valor asociado a la clave "A1" en el mapa tablaAS, que es un
         * objeto HashMap.
         * Luego, se llama al método put en ese objeto HashMap para agregar una nueva
         * entrada.
         */
        tablaAS.get("A1").put(TipoToken.FROM, Arrays.asList());
        tablaAS.get("A1").put(TipoToken.EOF, Arrays.asList());

        // A2 -> idA3
        tablaAS.put("A2", new HashMap<>());
        /*
         * Se crea se crea una nueva entrada en el mapa tablaAS utilizando el no
         * terminal "A2" como clave.
         * /*
         * se obtiene el valor asociado a la clave "A2" en el mapa tablaAS, que es un
         * objeto HashMap.
         * Luego, se llama al método put en ese objeto HashMap para agregar una nueva
         * entrada.
         */
        tablaAS.get("A2").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR, "A3"));

        // A3 -> .id | (epsilon)
        tablaAS.put("A3", new HashMap<>());
        /*
         * Se crea se crea una nueva entrada en el mapa tablaAS utilizando el no
         * terminal "A3" como clave.
         * /*
         * se obtiene el valor asociado a la clave "A3" en el mapa tablaAS, que es un
         * objeto HashMap.
         * Luego, se llama al método put en ese objeto HashMap para agregar una nueva
         * entrada.
         */
        tablaAS.get("A3").put(TipoToken.COMA, Arrays.asList(TipoToken.PUNTO, TipoToken.IDENTIFICADOR));
        tablaAS.get("A3").put(TipoToken.FROM, Arrays.asList());
        tablaAS.get("A3").put(TipoToken.COMA, Arrays.asList());
        tablaAS.get("A3").put(TipoToken.EOF, Arrays.asList());

        /**
         * Se crean una entrada en el mapa tablaAS para el no terminal correspondiente
         * y asocian la regla de producción correspondiente a ese no terminal y al
         * terminal TipoToken.
         */

        // T -> T2T1
        tablaAS.put("T", new HashMap<>());
        tablaAS.get("T").put(TipoToken.IDENTIFICADOR, Arrays.asList("T2", "T1"));

        // T1 -> ,T | (epsilon)
        tablaAS.put("T1", new HashMap<>());
        tablaAS.get("T1").put(TipoToken.COMA, Arrays.asList(TipoToken.COMA, "T"));
        tablaAS.get("T1").put(TipoToken.EOF, Arrays.asList());

        // T2 -> idT3
        tablaAS.put("T2", new HashMap<>());
        tablaAS.get("T2").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR, "T3"));
        tablaAS.get("T2").put(TipoToken.EOF, Arrays.asList());

        // T3 -> id | (epsilon)
        tablaAS.put("T3", new HashMap<>());
        tablaAS.get("T3").put(TipoToken.IDENTIFICADOR, Arrays.asList(TipoToken.IDENTIFICADOR));
        tablaAS.get("T3").put(TipoToken.COMA, Arrays.asList());
        tablaAS.get("T3").put(TipoToken.EOF, Arrays.asList());
    }

    /**
     * Obtiene la regla de producción correspondiente a un no terminal y terminal
     * específico.
     * 
     * @param noTerminal el no terminal de la regla.
     * @param terminal   el terminal de la regla.
     * @return la lista de objetos que representa la regla de producción.
     */
    public List<Object> getProduccion(String noTerminal, TipoToken terminal) {
        return tablaAS.getOrDefault(noTerminal, new HashMap<>()).get(terminal);
    }

}
