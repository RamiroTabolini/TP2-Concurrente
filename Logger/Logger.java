package Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
    private PrintWriter writer;
    private PrintWriter errorWriter;
    private PrintWriter infoWriter;

    private static Logger instance;
    private static final Object lock = new Object();

    private static final String LOG_FILE = "log.txt"; // aqui se guardan todos los logs
    private static final String ERROR_FILE = "error.txt"; // aqui se guardan los errores
    private static final String INFO_FILE = "info.txt"; // aqui se guardan los logs de info

    private Logger() {
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(LOG_FILE, false)));
            errorWriter = new PrintWriter(new BufferedWriter(new FileWriter(ERROR_FILE, false)));
            infoWriter = new PrintWriter(new BufferedWriter(new FileWriter(INFO_FILE, false)));
            logInfo(LOG_FILE + " y " + ERROR_FILE + " y " + INFO_FILE + " han sido creados.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    try {
                        instance = new Logger();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
        }
        return instance;
    }

    public synchronized void log(String tipo, String mensaje) {

        String time = java.time.LocalDateTime.now().toString();
        String logLine = String.format("[%s] - [%s] - [%s] : %s", Thread.currentThread().getName(), time, tipo, mensaje);


        try {
            switch (tipo) {
                case "ERROR":
                    errorWriter.println(logLine);
                    errorWriter.flush();
                    break;
                case "INFO":
                    infoWriter.println(logLine);
                    infoWriter.flush();
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

    public synchronized void cerrar() {
        if (writer != null && errorWriter != null && infoWriter != null) {
            logInfo("Cerrando Logger y liberando recursos.");
            writer.flush();
            infoWriter.flush();

            writer.close();
            errorWriter.close();
            infoWriter.close();
        }
    }

}
