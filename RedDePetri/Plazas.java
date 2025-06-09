package RedDePetri;

import java.util.HashMap;
import java.util.Map;

public class Plazas {
    private Map<Integer, Integer> tokensPorPlaza;

    public Plazas() {
        this.tokensPorPlaza = new HashMap<>();
    }

    public void agregarPlaza(Integer idPlaza, Integer cantidadTokens) {
        if (idPlaza == null || cantidadTokens == null) {
            throw new IllegalArgumentException("El ID de la plaza y la cantidad de tokens no pueden ser nulos.");
        }
        if (tokensPorPlaza.containsKey(idPlaza)) {
            throw new IllegalArgumentException("La plaza " + idPlaza + " ya existe.");
        }
        if (cantidadTokens < 0) {
            throw new IllegalArgumentException("La cantidad de tokens no puede ser negativa.");
        }
        tokensPorPlaza.put(idPlaza, cantidadTokens);
    }

    public void agregarTokensPorPlaza(Integer idPlaza, Integer cantidadTokens) {
        if (idPlaza == null || cantidadTokens == null) {
            throw new IllegalArgumentException("El ID de la plaza y la cantidad de tokens no pueden ser nulos.");
        }
        if (!tokensPorPlaza.containsKey(idPlaza)) {
            throw new IllegalArgumentException("La plaza " + idPlaza + " no existe.");
        }
        if (cantidadTokens < 0) {
            throw new IllegalArgumentException("La cantidad de tokens a agregar no puede ser negativa.");
        }
        tokensPorPlaza.put(idPlaza, obtenerCantidadTokensPorPlaza(idPlaza) + cantidadTokens);
    }

    public void eliminarTokensPorPlaza(Integer idPlaza, Integer cantidadTokens) {
        if (tokensPorPlaza.containsKey(idPlaza)) {
            int tokensActuales = tokensPorPlaza.get(idPlaza);
            if (tokensActuales >= cantidadTokens) {
                tokensPorPlaza.put(idPlaza, tokensActuales - cantidadTokens);
            } else {
                throw new IllegalArgumentException("No hay suficientes tokens en la plaza " + idPlaza);
            }
        } else {
            throw new IllegalArgumentException("La plaza " + idPlaza + " no existe.");
        }
    }

    public Integer obtenerCantidadTokensPorPlaza(Integer idPlaza) {
        if (idPlaza == null) {
            throw new IllegalArgumentException("El ID de la plaza no puede ser nulo.");
        }
        if (!tokensPorPlaza.containsKey(idPlaza)) {
            throw new IllegalArgumentException("La plaza " + idPlaza + " no existe.");
        }
        return tokensPorPlaza.getOrDefault(idPlaza, 0);
    }
    
    public Map<Integer, Integer> obtenerTokensPorPlaza() {
        return new HashMap<>(tokensPorPlaza);
    }
}
