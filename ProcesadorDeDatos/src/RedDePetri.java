import java.util.HashMap;

public class RedDePetri {
    private final int[][] matrizIncidencia = {
            //T0 T1 T2 T3 T4 T5 T6 T7 T8 T9 T10 T11
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, -1, 0, 0, -1, 0, -1, 0, 0, 0, 0},
            {0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, -1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, -1, 0, 1, -1, 1, -1, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, -1, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, -1},
    };
    private int[] marcadoActual;

    private final HashMap<String, int[]> transiciones;
    private HashMap<String, Boolean> transicionesSensibilizadas;

    public RedDePetri() {
        transiciones = new HashMap<>() {
            {
                put("T0", new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }

            {
                put("T1", new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }

            {
                put("T2", new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            }

            {
                put("T3", new int[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0});
            }

            {
                put("T4", new int[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0});
            }

            {
                put("T5", new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0});
            }

            {
                put("T6", new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0});
            }

            {
                put("T7", new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0});
            }

            {
                put("T8", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0});
            }

            {
                put("T9", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0});
            }

            {
                put("T10", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0});
            }

            {
                put("T11", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
            }
        };

        //                       P0 P1 P2 P3 P4 P5 P6 P7 P8 P9 P10 P11
        marcadoActual = new int[]{3, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0};

        transicionesSensibilizadas = new HashMap<String, Boolean>();
        transicionesSensibilizadas.put("T0", false);
        transicionesSensibilizadas.put("T1", false);
        transicionesSensibilizadas.put("T2", false);
        transicionesSensibilizadas.put("T3", false);
        transicionesSensibilizadas.put("T4", false);
        transicionesSensibilizadas.put("T5", false);
        transicionesSensibilizadas.put("T6", false);
        transicionesSensibilizadas.put("T7", false);
        transicionesSensibilizadas.put("T8", false);
        transicionesSensibilizadas.put("T9", false);
        transicionesSensibilizadas.put("T10", false);
        transicionesSensibilizadas.put("T11", false);
    }

    public int[] getSiguienteEstado(int[] transicion) {
        int[] marcadoSiguiente = new int[matrizIncidencia.length];

        for (int f = 0; f < matrizIncidencia.length; f++) {
            int productoPunto = 0;
            for (int c = 0; c < matrizIncidencia[f].length; c++) {
                productoPunto += matrizIncidencia[f][c] * transicion[c];
            }
            marcadoSiguiente[f] = marcadoActual[f] + productoPunto;
        }
        return marcadoSiguiente;
    }

    public boolean[] transicionesSensibilizadas() {
        boolean[] sensibilizadas = new boolean[]{true, true, true, true, true, true, true, true,true, true, true, true};

        for (int t = 0; t < getTransiciones().size(); t++) {
            int[] marcado = getSiguienteEstado(getTransiciones().get("T" + t));

            for (int marca : marcado) {
                if (marca < 0) {
                    sensibilizadas[t] = false;
                    break;
                }
            }
        }

        return sensibilizadas;
    }

    public HashMap<String, int[]> getTransiciones() {
        return transiciones;
    }

    public void dispararTransicion(int transicion){
        if(transicion >= 0 && transicion < 12){
            marcadoActual = getSiguienteEstado(getTransiciones().get("T" + transicion));
        }
    }

    public void actualizarTransiciones(){
        boolean[] sensibilizadas = transicionesSensibilizadas();
        boolean estadoTransicion;
        for (int i = 0; i < getTransiciones().size(); i++){
            estadoTransicion = sensibilizadas[i];
            transicionesSensibilizadas.put("T" + 1, estadoTransicion);
        }
    }
}
