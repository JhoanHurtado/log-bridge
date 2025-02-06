package io.github.jhoanhurtado.enums;

/**
 * Representa los diferentes niveles de severidad para el registro de eventos en el sistema de logs.
 * <p>
 * Cada nivel de log indica la importancia o criticidad del mensaje registrado, permitiendo 
 * categorizar los eventos y facilitar la depuración de la aplicación.
 * </p>
 * <p>
 * Los niveles disponibles son:
 * </p>
 * <ul>
 *   <li>{@link #DEBUG} - Información detallada utilizada principalmente para diagnóstico y depuración.</li>
 *   <li>{@link #INFO} - Mensajes informativos sobre el estado general de la aplicación.</li>
 *   <li>{@link #WARN} - Advertencias sobre posibles problemas que no interrumpen la ejecución.</li>
 *   <li>{@link #ERROR} - Errores que pueden afectar el funcionamiento pero permiten continuar la ejecución.</li>
 *   <li>{@link #CRITICAL} - Fallos graves que pueden llevar a la terminación de la aplicación.</li>
 * </ul>
 */
public enum LogLevel {
    /** Información detallada utilizada principalmente para diagnóstico y depuración. */
    DEBUG,

    /** Mensajes informativos sobre el estado general de la aplicación. */
    INFO,

    /** Advertencias sobre posibles problemas que no interrumpen la ejecución. */
    WARN,

    /** Errores que pueden afectar el funcionamiento pero permiten continuar la ejecución. */
    ERROR,

    /** Fallos graves que pueden llevar a la terminación de la aplicación. */
    CRITICAL
}