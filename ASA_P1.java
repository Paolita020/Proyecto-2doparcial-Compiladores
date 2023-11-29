import java.util.List;
import java.util.Stack;
import java.util.Arrays;

public class ASA implements Parser{

    private int i = 0;
    private boolean hayErrores = false;
    private final List<Token> tokens;
    String [][] TablaAccionGoto  =                                                                                      //
    { {"Estado",      "select",     "from",      "distinct",      "*",       ",",        "id",        ".",      "$",         "Q",     "D",     "P",     "A",      "A1",      "A2",      "A3",     "T",       "T1",      "T2",     "T3",    },
      {     "0",        "s 1",      "",             "",           "",        "",          "",         "",       "",          "24",    "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },             
      {     "1",         "",        "",             "s 3",        "s 5",     "",          "s 8",      "",       "",          "",      "2",     "4",     "6",      "",        "7",       "",       "",        "",        "",       "",      },
      {     "2",         "",        "s 9",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },  
      {     "3",         "",        "",             "",           "s 5",     "",          "s 8",      "",       "",          "",      "",      "10",    "6",      "",        "7",       "",       "",        "",        "",       "",      }, 
      {     "4",         "",        "r 2",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "5",         "",        "r 3",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "6",         "",        "r 3",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "7",         "",        "r 7",          "",           "",       "s 12",       "",         "",       "",          "",      "",      "",      "",       "11",      "",        "",       "",        "",        "",       "",      },
      {     "8",         "",        "r 10",         "",           "",       "r 10",       "",         "s 14",   "",          "",      "",      "",      "",       "",        "",        "13",     "",        "",        "",       "",      },
      {     "9",         "",        "",             "",           "",        "",          "s 19",     "",       "",          "",      "",      "",      "",       "",        "",        "",       "15",      "",        "18",     "",      },
      {     "10",        "",        "r 1",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },             
      {     "11",        "",        "r 5",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "12",        "",        "",             "",           "",        "",          "s 8",      "",       "",          "",      "",      "",      "16",     "",        "7",       "",       "",        "",        "",       "",      },  
      {     "13",        "",        "r 8",          "",           "",        "r 8",       "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      }, 
      {     "14",        "",        "",             "",           "",        "",          "s 17",     "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "15",        "",        "",             "",           "",        "",          "",         "",       "r 0",       "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "16",        "",        "r 6",          "",           "",        "",          "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "17",        "",        "r 9",          "",           "",        "r 9",       "",         "",       "",          "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "18",        "",        "",             "",           "",       "s 21",       "",         "",       "r 13",      "",      "",      "",      "",       "",        "",        "",       "",        "20",      "",       "",      },
      {     "19",        "",        "",             "",           "",       "r 16",       "s 23",     "",       "r 16",      "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "22",    },
      {     "20",        "",        "",             "",           "",        "",          "",         "",       "r 11",      "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },             
      {     "21",        "",        "",             "",           "",        "",         "s 19",      "",       "",          "",      "",      "",      "",       "",        "",        "",       "25",      "",        "18",     "",      },
      {     "22",        "",        "",             "",           "",       "r 14",       "",         "",       "r 14",      "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },  
      {     "23",        "",        "",             "",           "",       "r 15",       "",         "",       "r 15",      "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      }, 
      {     "24",        "",        "",             "",           "",        "",          "",         "",       "acc",       "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      },
      {     "25",        "",        "",             "",           "",        "",          "",         "",       "r 12",      "",      "",      "",      "",       "",        "",        "",       "",        "",        "",       "",      }
    };
    String [][] Reducciones = 
    { { "Q","4"  },
      { "D","2"  },
      { "D","1"  },
      { "P","1"  },
      { "P","1"  },
      { "A","2"  },
      { "A1","2" },
      { "A1","0" },
      { "A2","2" },
      { "A3","2" },
      { "A3","0" },
      { "T","2"  },
      { "T1","2" },
      { "T1","0" },
      { "T2","2" },
      { "T3","1" },
      { "T3","0" }
    };   


    public ASA(List<Token> tokens){
        this.tokens = tokens;
    }

    @Override
    public boolean parse() {

        String entrada = "";
        //boolean terminado = false;
        Stack <String> pila = new Stack <String>();

        entrada = avanzarEntrada();
        pila.push("0");
        while(true){
            String[] accion = TablaAccionGoto[buscaEstado(TablaAccionGoto, pila.peek() )][buscaColumna(TablaAccionGoto, entrada )].split(" ");
            if ( accion[0].equals("s") ){
            	pila.push(accion[1]);
                entrada = avanzarEntrada();
            }
            else if(  accion[0].equals("r") ){
            	for(int x=0;x<Integer.parseInt(Reducciones[Integer.parseInt(accion[1])][1]);x++){
                	pila.pop();
            	}
                String goTo = TablaAccionGoto[buscaEstado(TablaAccionGoto, pila.peek() )][buscaColumna(TablaAccionGoto, Reducciones[Integer.parseInt(accion[1])][0] )];
                pila.push(goTo);
            }
            else if( accion[0].equals("acc") ){
                System.out.println("Consulta correcta");
                return  true;
            }else{
                System.out.println("Si no sabe no le mueva pai");
                return false;
            }
        }
    }

    private String avanzarEntrada(){
    	String entradaBuffer;
        if(tokens.get(i).tipo == TipoToken.IDENTIFICADOR )
            entradaBuffer = "id";
        else 
            entradaBuffer = tokens.get(i).lexema;
       	i++;
       	return entradaBuffer;
    }

    private int buscaEstado(String tablaAccionGoto [][], String estado){
        int l = tablaAccionGoto.length;
        for(int i=1;i<l;i++){
            if(tablaAccionGoto[i][0].equals(estado) )
                return i;
        }
        return -10;
    }

    private int buscaColumna(String tablaAccionGoto [][], String columna){
        int l = tablaAccionGoto[0].length;
        for(int i=1;i<l;i++){
            if(tablaAccionGoto[0][i].equals(columna) )
                return i;
        }
        return -1;
    }
}
