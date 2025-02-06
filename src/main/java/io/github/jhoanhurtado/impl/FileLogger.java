package io.github.jhoanhurtado.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.github.jhoanhurtado.enums.LogLevel;
import io.github.jhoanhurtado.interfaces.Logger;

/**
 * Implementación de {@link Logger} que registra mensajes en un archivo de
 * texto.
 * <p>
 * Esta clase permite almacenar registros en un archivo especificado, asegurando
 * que el archivo exista antes de escribir en él. Cada entrada en el log incluye
 * una marca de tiempo y un nivel de severidad.
 * </p>
 */
public class FileLogger implements Logger {

    /**
     * Ruta del archivo donde se almacenarán los registros.
     */
    private final String logFilePath;

    /**
     * Construye una nueva instancia de {@code FileLogger}.
     * <p>
     * Este constructor inicializa un objeto {@code FileLogger} utilizando la
     * ruta del archivo de registro proporcionada como parámetro. La ruta del
     * archivo de registro es esencial para almacenar los mensajes de log en el
     * archivo correspondiente. La ruta se obtiene a partir de la configuración
     * definida al crear una instancia de {@code FileLogger}.
     * </p>
     *
     * @param pathFile La ruta completa del archivo de registro donde se
     * guardarán los logs generados por el logger. Si la ruta no existe, puede
     * ser necesario crear el archivo o los directorios correspondientes.
     */
    public FileLogger(String pathFile) {
        this.logFilePath = pathFile;
    }

    /**
     * Registra un mensaje en el archivo de log con el nivel de severidad
     * especificado.
     * <p>
     * Este método realiza las siguientes acciones:
     * </p>
     * <ul>
     * <li>Verifica si el archivo de registro existe; si no, lo crea.</li>
     * <li>Si el archivo es creado, registra un mensaje informativo indicando la
     * creación.</li>
     * <li>Escribe el mensaje de log en el archivo con un formato de fecha y
     * nivel de severidad.</li>
     * </ul>
     * <p>
     * En caso de error de entrada/salida, se registra un mensaje de error en el
     * log.
     * </p>
     *
     * @param message El mensaje que se desea registrar.
     * @param level El nivel de severidad del mensaje, definido en
     * {@link LogLevel}.
     */
    @Override
    public void log(String message, LogLevel level) {
        try {
            // Verifica si el archivo existe, si no, lo crea
            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                boolean isFileCreated = logFile.createNewFile();
                if (isFileCreated) {
                    log("Log file created: " + logFilePath, LogLevel.INFO);
                }
            }

            // Escribe en el archivo de registro
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.println(timestamp + " [" + level + "] " + message);
            }
        } catch (IOException e) {
            log(String.format("Error writing to log file: %s", e.getMessage()), LogLevel.ERROR);
        }
    }
}
