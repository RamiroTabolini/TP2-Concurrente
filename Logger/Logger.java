package Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
    private PrintWriter writer;
    private PrintWriter errorWriter;
    private PrintWriter infoWriter;

    private static Logger instance;

    private static final String LOG_FILE = "log.txt"; // aqui se guardan todos los logs
    private static final String ERROR_FILE = "error.txt"; // aqui se guardan los errores 
    private static final String INFO_FILE = "info.txt"; // aqui se guardan los logs de info

    private Logger() {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, false))); // false para sobrescribir el archivo
            errorWriter = new PrintWriter(new BufferedWriter(new FileWriter(ERROR_FILE, false)));
            infoWriter = new PrintWriter(new BufferedWriter(new FileWriter(INFO_FILE, false)));
            logInfo(LOG_FILE + " y " + ERROR_FILE + " y " + INFO_FILE + " han sido creados.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String tipo, String mensaje){

        String time = java.time.LocalDateTime.now().toString();
        String logLine = String.format("[%s] [%s] %s", time, tipo, mensaje);
        
        try {
            switch (tipo) {
                case "ERROR":
                    errorWriter.println(logLine);
                    break;
                case "INFO":
                    infoWriter.println(logLine);
                    break;
            }
            writer.println(logLine);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logError(String mensaje) {
        log("ERROR", mensaje);
    }

    public void logInfo(String mensaje) {
        log("INFO", mensaje);
    }

    public void cerrar() {
        if (writer != null) {
            writer.close();
            errorWriter.close();
            infoWriter.close();
        }
    }
    
}
