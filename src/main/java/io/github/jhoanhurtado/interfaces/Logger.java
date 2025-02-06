package io.github.jhoanhurtado.interfaces;

import io.github.jhoanhurtado.enums.LogLevel;

/**
 * Interfaz que define la funcionalidad de un sistema de registro de logs.
 * <p>
 * Proporciona un método para registrar mensajes con diferentes niveles de severidad.
 * Implementaciones de esta interfaz pueden definir distintos mecanismos de almacenamiento,
 * como archivos, bases de datos o sistemas de monitoreo en la nube.
 * </p>
 */
public interface Logger {

    /**
     * Registra un mensaje con un nivel de log especificado.
     * <p>
     * Este método permite almacenar mensajes con distintos niveles de severidad, 
     * facilitando la depuración y monitoreo de aplicaciones.
     * </p>
     *
     * @param message El mensaje que se desea registrar.
     * @param level   El nivel de severidad del mensaje. Puede ser uno de los valores definidos en {@link LogLevel}.
     */
    void log(String message, LogLevel level);
}