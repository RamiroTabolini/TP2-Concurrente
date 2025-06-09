package RedDePetri;

import java.util.Map;

public class RdP {
    Plazas plazas;
    Map<Integer, Transicion> transiciones;

    public RdP() {
        construirRdP();
    }

    private void construirRdP() {
        plazas = new Plazas();

        plazas.agregarPlaza(0, 3);
        plazas.agregarPlaza(1, 0);
        plazas.agregarPlaza(2, 1);
        plazas.agregarPlaza(3, 0);
        plazas.agregarPlaza(4, 0);
        plazas.agregarPlaza(5, 0);
        plazas.agregarPlaza(6, 1);
        plazas.agregarPlaza(7, 0);
        plazas.agregarPlaza(8, 0);
        plazas.agregarPlaza(9, 0);
        plazas.agregarPlaza(10, 0);
        plazas.agregarPlaza(11, 0);

        Transicion t0 = new Transicion(0, Map.of(1, 1), Map.of(0, 1));

        Transicion t1 = new Transicion(1, Map.of(3, 1, 2, 1), Map.of(1, 1));

        Transicion t2 = new Transicion(2, Map.of(4, 1), Map.of(3, 1, 6, 1));

        Transicion t3 = new Transicion(3, Map.of(5, 1), Map.of(4, 1));

        Transicion t4 = new Transicion(4, Map.of(11, 1, 6, 1), Map.of(5, 1));

        Transicion t5 = new Transicion(5, Map.of(7, 1), Map.of(3, 1, 6, 1));

        Transicion t6 = new Transicion(6, Map.of(11, 1, 6, 1), Map.of(7, 1));

        Transicion t7 = new Transicion(7, Map.of(8, 1), Map.of(3, 1, 6, 1));

        Transicion t8 = new Transicion(8, Map.of(9, 1), Map.of(8, 1));

        Transicion t9 = new Transicion(9, Map.of(10, 1), Map.of(9, 1));

        Transicion t10 = new Transicion(10, Map.of(11, 1, 6, 1), Map.of(10, 1));

        Transicion t11 = new Transicion(11, Map.of(0, 1), Map.of(11, 1));

        transiciones = new java.util.HashMap<>();
        transiciones.put(0, t0);
        transiciones.put(1, t1);
        transiciones.put(2, t2);
        transiciones.put(3, t3);
        transiciones.put(4, t4);
        transiciones.put(5, t5);
        transiciones.put(6, t6);
        transiciones.put(7, t7);
        transiciones.put(8, t8);
        transiciones.put(9, t9);
        transiciones.put(10, t10);
        transiciones.put(11, t11);

    }

    public void mostrarEstado() {
        System.out.println("Estado actual de las plazas:");
        for (Map.Entry<Integer, Integer> entry : plazas.obtenerTokensPorPlaza().entrySet()) {
            System.out.println("Plaza " + entry.getKey() + ": " + entry.getValue() + " tokens");
        }
    }
}