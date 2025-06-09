import Logger.Logger;
import RedDePetri.RdP;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        RdP redDePetri = new RdP();      
        redDePetri.mostrarEstado();
        
        logger.cerrar();
    }
}