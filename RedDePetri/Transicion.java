package RedDePetri;

import java.util.Map;

import Logger.Logger;

public class Transicion {
    private final Integer idTransicion;
    private Map<Integer, Integer> postIncidencia;
    private Map<Integer, Integer> preIncidencia;
    private static final Logger logger = Logger.getInstance();

    public Transicion(Integer idTransicion, Map<Integer, Integer> postIncidencia, Map<Integer, Integer> preIncidencia) {
        this.idTransicion = idTransicion;
        this.postIncidencia = postIncidencia;
        this.preIncidencia = preIncidencia;
        logger.logInfo("Se ha creado una nueva transición con ID: " + idTransicion);
    }

    public Boolean puedeDispararse(Plazas plazas) {
        if (plazas == null) {
            logger.logError("Intento de verificar si la transición puede dispararse con plazas nulas.");
            throw new IllegalArgumentException("Las plazas no pueden ser nulas.");
        }
        for (Map.Entry<Integer, Integer> entry : preIncidencia.entrySet()) {
            Integer idPlaza = entry.getKey();
            Integer cantidadTokensNecesarios = entry.getValue();
            if (plazas.obtenerCantidadTokensPorPlaza(idPlaza) < cantidadTokensNecesarios) {
                return false;
            }
        }
        logger.logInfo("La transición " + idTransicion + " puede dispararse.");
        return true;
    }

    public void disparar(Plazas plazas) {
        if (plazas == null) {
            logger.logError("Intento de disparar una transición con plazas nulas.");
            throw new IllegalArgumentException("Las plazas no pueden ser nulas.");
        }
        if (!puedeDispararse(plazas)) {
            logger.logError("La transición " + idTransicion + " no puede dispararse debido a la falta de tokens en las plazas requeridas.");
            throw new IllegalStateException("La transición " + idTransicion + " no puede dispararse debido a la falta de tokens en las plazas requeridas.");
        }
        for (Map.Entry<Integer, Integer> entry : preIncidencia.entrySet()) {
            Integer idPlaza = entry.getKey();
            Integer cantidadTokensNecesarios = entry.getValue();
            plazas.eliminarTokensPorPlaza(idPlaza, cantidadTokensNecesarios);
            logger.logInfo("Se han eliminado " + cantidadTokensNecesarios + " tokens de la plaza " + idPlaza + " al disparar la transición " + idTransicion + ".");
        }
        for (Map.Entry<Integer, Integer> entry : postIncidencia.entrySet()) {
            Integer idPlaza = entry.getKey();
            Integer cantidadTokensAAgregar = entry.getValue();
            plazas.agregarTokensPorPlaza(idPlaza, cantidadTokensAAgregar);
            logger.logInfo("Se han agregado " + cantidadTokensAAgregar + " tokens a la plaza " + idPlaza + " al disparar la transición " + idTransicion + ".");
        }
    }
}
