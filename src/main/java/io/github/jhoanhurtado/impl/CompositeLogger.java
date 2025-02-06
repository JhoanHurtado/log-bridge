package io.github.jhoanhurtado.impl;

import java.util.List;

import io.github.jhoanhurtado.enums.LogLevel;
import io.github.jhoanhurtado.interfaces.Logger;

/**
 * Implementación de {@link Logger} que permite registrar mensajes en múltiples loggers simultáneamente.
 * <p>
 * Esta clase actúa como un contenedor de múltiples instancias de {@link Logger} y distribuye los mensajes de log 
 * a todos los loggers configurados. Es útil cuando se desea registrar eventos en diferentes destinos, 
 * como archivos, bases de datos o sistemas de monitoreo.
 * </p>
 */
public class CompositeLogger implements Logger {

    /** Lista de loggers a los que se enviarán los mensajes de log. */
    private final List<Logger> loggers;

    /**
     * Constructor que inicializa el {@code CompositeLogger} con una lista de loggers.
     *
     * @param loggers Lista de instancias de {@link Logger} donde se enviarán los mensajes de log.
     *               No debe ser {@code null} y debe contener al menos un logger.
     */
    public CompositeLogger(List<Logger> loggers) {
        this.loggers = loggers;
    }

    /**
     * Registra un mensaje en todos los loggers configurados.
     * <p>
     * Itera sobre la lista de loggers y envía el mensaje a cada uno de ellos con el nivel especificado.
     * </p>
     *
     * @param message El mensaje que se desea registrar.
     * @param level   El nivel de severidad del mensaje, definido en {@link LogLevel}.
     */
    @Override
    public void log(String message, LogLevel level) {
        for (Logger logger : loggers) {
            logger.log(message, level);
        }
    }
}