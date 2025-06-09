import Logger.Logger;
import RedDePetri.RdP;


public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.logInfo("Iniciando la construcción de la Red de Petri...");

        RdP redDePetri = new RdP();
        logger.logInfo("Red de Petri construida exitosamente.");
        
        logger.logInfo("Mostrando el estado inicial de la Red de Petri:");
        redDePetri.mostrarEstado();
        
        logger.logInfo("Fin de la ejecución del programa.");
        
        logger.logInfo("Estado final de la Red de Petri:");
        redDePetri.mostrarEstado();
        
        logger.logInfo("Programa finalizado correctamente.");

        logger.logError("null pointer exception");
        logger.cerrar();
    }
}