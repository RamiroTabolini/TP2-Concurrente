package Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
    private PrintWriter writer;
    private static Logger instance;
    private static final String LOG_FILE = "log.txt"; // aqui se guardan todos los logs
    private static final String ERROR_FILE = "error.txt"; // aqui se guardan los errores 
    private static final String DEBUG_FILE = "debug.txt"; // aqui se guardan los logs de debug
    private static final String INFO_FILE = "info.txt"; // aqui se guardan los logs de info
    private static final String WARN_FILE = "warn.txt"; // aqui se guardan los logs de advertencia
    private static final String FATAL_FILE = "fatal.txt"; // aqui se guardan los logs de fatal
    private static final String TRACE_FILE = "trace.txt"; // aqui se guardan los logs de traza

    private Logger() {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, false))); // false para sobrescribir el archivo
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

        String timestamp = java.time.LocalDateTime.now().toString();
        String logLine = String.format("[%s] [%s] %s", timestamp, tipo, mensaje);
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, true)));
            writer.println(logLine);
            writer.flush();
            // Escribir en el archivo correspondiente según el tipo de log
            switch (tipo) {
                case "ERROR":
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(ERROR_FILE, true)));
                    break;
                case "DEBUG":
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(DEBUG_FILE, true)));
                    break;
                case "INFO":
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(INFO_FILE, true)));
                    break;
                case "WARN":
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(WARN_FILE, true)));
                    break;
                case "FATAL":
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(FATAL_FILE, true)));
                    break;
                case "TRACE":
                    writer = new PrintWriter(new BufferedWriter(new FileWriter(TRACE_FILE, true)));
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

    public void logDebug(String mensaje) {
        log("DEBUG", mensaje);
    }

    public void logInfo(String mensaje) {
        log("INFO", mensaje);
    }

    public void logWarn(String mensaje) {
        log("WARN", mensaje);
    }

    public void logFatal(String mensaje) {
        log("FATAL", mensaje);
    }

    public void logTrace(String mensaje) {
        log("TRACE", mensaje);
    }

    public void cerrar() {
        if (writer != null) {
            writer.close();
        }
    }
    
}
