package RedDePetri;

import java.util.HashMap;
import java.util.Map;

import Logger.Logger;

public class Plazas {
    private Map<Integer, Integer> tokensPorPlaza;
    private static final Logger logger = Logger.getInstance();

    public Plazas() {
        this.tokensPorPlaza = new HashMap<>();
        logger.logInfo("Se ha creado una nueva instancia de Plazas.");
    }

    public void agregarPlaza(Integer idPlaza, Integer cantidadTokens) {
        if (idPlaza == null || cantidadTokens == null) {
            logger.logError("Intento de agregar una plaza con ID nulo o cantidad de tokens nula.");
            throw new IllegalArgumentException("El ID de la plaza y la cantidad de tokens no pueden ser nulos.");
        }
        if (tokensPorPlaza.containsKey(idPlaza)) {
            logger.logError("Intento de agregar una plaza que ya existe: " + idPlaza);
            throw new IllegalArgumentException("La plaza " + idPlaza + " ya existe.");
        }
        if (cantidadTokens < 0) {
            logger.logError("Intento de agregar una plaza con cantidad de tokens negativa: " + cantidadTokens);
            throw new IllegalArgumentException("La cantidad de tokens no puede ser negativa.");
        }
        tokensPorPlaza.put(idPlaza, cantidadTokens);
        logger.logInfo("Se ha agregado la plaza " + idPlaza + " con " + cantidadTokens + " tokens.");
    }

    public void agregarTokensPorPlaza(Integer idPlaza, Integer cantidadTokens) {
        if (idPlaza == null || cantidadTokens == null) {
            logger.logError("Intento de agregar tokens a una plaza con ID nulo o cantidad de tokens nula.");
            throw new IllegalArgumentException("El ID de la plaza y la cantidad de tokens no pueden ser nulos.");
        }
        if (!tokensPorPlaza.containsKey(idPlaza)) {
            logger.logError("Intento de agregar tokens a una plaza que no existe: " + idPlaza);
            throw new IllegalArgumentException("La plaza " + idPlaza + " no existe.");
        }
        if (cantidadTokens < 0) {
            logger.logError("Intento de agregar una cantidad negativa de tokens: " + cantidadTokens);
            throw new IllegalArgumentException("La cantidad de tokens a agregar no puede ser negativa.");
        }
        tokensPorPlaza.put(idPlaza, obtenerCantidadTokensPorPlaza(idPlaza) + cantidadTokens);
        logger.logInfo("Se han agregado " + cantidadTokens + " tokens a la plaza " + idPlaza + ".");
    }

    public void eliminarTokensPorPlaza(Integer idPlaza, Integer cantidadTokens) {
        if (tokensPorPlaza.containsKey(idPlaza)) {
            int tokensActuales = tokensPorPlaza.get(idPlaza);
            if (tokensActuales >= cantidadTokens) {
                tokensPorPlaza.put(idPlaza, tokensActuales - cantidadTokens);
                logger.logInfo("Se han eliminado " + cantidadTokens + " tokens de la plaza " + idPlaza + ".");
            } else {
                logger.logError("Intento de eliminar más tokens de los que hay en la plaza " + idPlaza + ": " + cantidadTokens + " solicitados, " + tokensActuales + " disponibles.");
                throw new IllegalArgumentException("No hay suficientes tokens en la plaza " + idPlaza);
            }
        } else {
            logger.logError("Intento de eliminar tokens de una plaza que no existe: " + idPlaza);
            throw new IllegalArgumentException("La plaza " + idPlaza + " no existe.");
        }
    }

    public Integer obtenerCantidadTokensPorPlaza(Integer idPlaza) {
        if (idPlaza == null) {
            logger.logError("Intento de obtener tokens de una plaza con ID nulo.");
            throw new IllegalArgumentException("El ID de la plaza no puede ser nulo.");
        }
        if (!tokensPorPlaza.containsKey(idPlaza)) {
            logger.logError("Intento de obtener tokens de una plaza que no existe: " + idPlaza);
            throw new IllegalArgumentException("La plaza " + idPlaza + " no existe.");
        }
        return tokensPorPlaza.getOrDefault(idPlaza, 0);
    }
    
    public Map<Integer, Integer> obtenerTokensPorPlaza() {
        return new HashMap<>(tokensPorPlaza);
    }
}
