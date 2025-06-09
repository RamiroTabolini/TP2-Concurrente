package Monitor;

import java.util.Map;

import Logger.Logger;
import RedDePetri.Plazas;
import RedDePetri.Transicion;



public class Monitor implements MonitorInterface{
    private Plazas plazas;
    private Map<Integer, Transicion> transiciones;
    private static final Logger logger = Logger.getInstance();
    
    public Monitor(Plazas plazas, Map<Integer, Transicion> transiciones) {
        this.plazas = plazas;
        this.transiciones = transiciones;
    }

    @Override
    public boolean fireTransition(int transition) {
        if (!transiciones.containsKey(transition)) {
            logger.logError("Intento de disparar una transición que no existe: " + transition);
            throw new IllegalArgumentException("La transición " + transition + " no existe.");
        }

        Transicion transicion = transiciones.get(transition);

        if (!transicion.puedeDispararse(plazas)){
            return false;
        }

        try {
            transicion.disparar(plazas);
            logger.logInfo("La transición " + transition + " ha sido disparada exitosamente.");
            return true;
        } catch (Exception e) {
            logger.logError("Error al disparar la transición " + transition + ": " + e.getMessage());
            return false;
        }
    }
}
