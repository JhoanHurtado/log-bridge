package io.github.jhoanhurtado.impl;

import java.util.Collections;

import io.github.jhoanhurtado.enums.LogLevel;
import io.github.jhoanhurtado.interfaces.Logger;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.cloudwatchlogs.model.CreateLogGroupRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.CreateLogStreamRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.InputLogEvent;
import software.amazon.awssdk.services.cloudwatchlogs.model.PutLogEventsRequest;
import software.amazon.awssdk.services.cloudwatchlogs.model.ResourceAlreadyExistsException;

/**
 * Implementación de {@link Logger} que registra mensajes en Amazon CloudWatch
 * Logs.
 * <p>
 * Esta clase se encarga de enviar eventos de log a un grupo y flujo de logs
 * específicos en AWS CloudWatch. Si el grupo o flujo de logs no existen, se
 * crean automáticamente.
 * </p>
 */
public class CloudWatchLogger implements Logger {

    /**
     * Cliente de AWS CloudWatch Logs utilizado para enviar registros.
     */
    private final CloudWatchLogsClient cloudWatchLogsClient;

    /**
     * Nombre del grupo de logs en CloudWatch.
     */
    private final String logGroupName;

    /**
     * Nombre del flujo de logs dentro del grupo en CloudWatch.
     */
    private final String logStreamName;

    /**
     * Construye una nueva instancia de {@code CloudWatchLogger} para registrar
     * mensajes en AWS CloudWatch Logs.
     * <p>
     * Este constructor inicializa el cliente de AWS CloudWatch Logs con la
     * región y las credenciales proporcionadas, utilizando la configuración de
     * la región y credenciales predeterminadas de AWS. También recibe los
     * nombres del grupo de logs y del flujo de logs que se utilizarán para
     * almacenar los mensajes.
     * </p>
     * <p>
     * Además, el constructor verifica si el grupo y el flujo de logs existen en
     * AWS CloudWatch. Si alguno de estos recursos no existe, se crea
     * automáticamente. Esto garantiza que la clase esté lista para registrar
     * mensajes en CloudWatch tan pronto como se instancie.
     * </p>
     *
     * @param region La región de AWS donde se encuentran los recursos de
     * CloudWatch Logs. Se utiliza para configurar el cliente de AWS CloudWatch
     * Logs.
     * @param logGroupName El nombre del grupo de logs en CloudWatch donde se
     * enviarán los registros.
     * @param logStreamName El nombre del flujo de logs dentro del grupo, en el
     * que se almacenarán los eventos de log.
     */
    public CloudWatchLogger(String region, String logGroupName, String logStreamName) {
        this.cloudWatchLogsClient = CloudWatchLogsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        this.logGroupName = logGroupName;
        this.logStreamName = logStreamName;

        createLogGroupIfNotExists();
        createLogStreamIfNotExists();
    }

    /**
     * Crea el grupo de logs en CloudWatch si aún no existe.
     */
    private void createLogGroupIfNotExists() {
        try {
            cloudWatchLogsClient.createLogGroup(CreateLogGroupRequest.builder()
                    .logGroupName(logGroupName)
                    .build());
        } catch (ResourceAlreadyExistsException ignored) {
            // El grupo de logs ya existe, no se requiere ninguna acción.
        }
    }

    /**
     * Crea el flujo de logs dentro del grupo en CloudWatch si aún no existe.
     */
    private void createLogStreamIfNotExists() {
        try {
            cloudWatchLogsClient.createLogStream(CreateLogStreamRequest.builder()
                    .logGroupName(logGroupName)
                    .logStreamName(logStreamName)
                    .build());
        } catch (ResourceAlreadyExistsException ignored) {
            // El flujo de logs ya existe, no se requiere ninguna acción.
        }
    }

    /**
     * Registra un mensaje en CloudWatch Logs con el nivel de severidad
     * especificado.
     * <p>
     * Este método envía un evento de log a AWS CloudWatch, con el siguiente
     * formato: <code>[NIVEL] Mensaje</code>, acompañado de una marca de tiempo
     * en milisegundos.
     * </p>
     *
     * @param message El mensaje que se desea registrar.
     * @param level El nivel de severidad del mensaje, definido en
     * {@link LogLevel}.
     */
    @Override
    public void log(String message, LogLevel level) {
        InputLogEvent logEvent = InputLogEvent.builder()
                .message("[" + level + "] " + message)
                .timestamp(System.currentTimeMillis())
                .build();

        cloudWatchLogsClient.putLogEvents(PutLogEventsRequest.builder()
                .logGroupName(logGroupName)
                .logStreamName(logStreamName)
                .logEvents(Collections.singletonList(logEvent))
                .build());
    }
}
