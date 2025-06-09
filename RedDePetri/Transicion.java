package RedDePetri;

import java.util.Map;

public class Transicion {
    private final Integer idTransicion;
    private Map<Integer, Integer> postIncidencia;
    private Map<Integer, Integer> preIncidencia;

    public Transicion(Integer idTransicion, Map<Integer, Integer> postIncidencia, Map<Integer, Integer> preIncidencia) {
        this.idTransicion = idTransicion;
        this.postIncidencia = postIncidencia;
        this.preIncidencia = preIncidencia;
    }

    public Boolean puedeDispararse(Plazas plazas) {
        if (plazas == null) {
            throw new IllegalArgumentException("Las plazas no pueden ser nulas.");
        }
        for (Map.Entry<Integer, Integer> entry : preIncidencia.entrySet()) {
            Integer idPlaza = entry.getKey();
            Integer cantidadTokensNecesarios = entry.getValue();
            if (plazas.obtenerCantidadTokensPorPlaza(idPlaza) < cantidadTokensNecesarios) {
                return false;
            }
        }
        return true;
    }

    public void disparar(Plazas plazas) {
        if (plazas == null) {
            throw new IllegalArgumentException("Las plazas no pueden ser nulas.");
        }
        if (!puedeDispararse(plazas)) {
            throw new IllegalStateException("La transición " + idTransicion + " no puede dispararse debido a la falta de tokens en las plazas requeridas.");
        }
        for (Map.Entry<Integer, Integer> entry : preIncidencia.entrySet()) {
            Integer idPlaza = entry.getKey();
            Integer cantidadTokensNecesarios = entry.getValue();
            plazas.eliminarTokensPorPlaza(idPlaza, cantidadTokensNecesarios);
        }
        for (Map.Entry<Integer, Integer> entry : postIncidencia.entrySet()) {
            Integer idPlaza = entry.getKey();
            Integer cantidadTokensAAgregar = entry.getValue();
            plazas.agregarTokensPorPlaza(idPlaza, cantidadTokensAAgregar);
        }
    }
}
