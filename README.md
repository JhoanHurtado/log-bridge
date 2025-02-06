# log-bridge

Librería para la gestión de logs

## Descripción

`log-bridge` es una librería que permite gestionar logs de manera eficiente y flexible. Proporciona múltiples implementaciones de loggers que pueden registrar mensajes en diferentes destinos, como archivos locales y Amazon CloudWatch Logs. Además, permite combinar múltiples loggers para registrar mensajes en varios destinos simultáneamente.

## Instalación

Para instalar la librería desde GitHub Packages, sigue estos pasos:

1. Agrega el repositorio de GitHub Packages a tu archivo `pom.xml` (si usas Maven):

    ```xml
    <repositories>
        <repository>
            <id>github</id>
            <url>https://maven.pkg.github.com/jhoanhurtado/log-bridge</url>
        </repository>
    </repositories>
    ```

2. Agrega la dependencia de `log-bridge` a tu archivo `pom.xml`:

    ```xml
    <dependency>
        <groupId>io.github.jhoanhurtado</groupId>
        <artifactId>log-bridge</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

## Uso

### Configuración de Loggers

#### Logger de Archivo

Para registrar mensajes en un archivo local, utiliza la clase `FileLogger`:

```java
import io.github.jhoanhurtado.impl.FileLogger;
import io.github.jhoanhurtado.enums.LogLevel;
import io.github.jhoanhurtado.interfaces.Logger;

public class Main {
    public static void main(String[] args) {
        Logger fileLogger = new FileLogger("/ruta/al/archivo.log");
        fileLogger.log("Mensaje de información", LogLevel.INFO);
    }
}
```

#### Logger de CloudWatch

Para registrar mensajes en Amazon CloudWatch Logs, utiliza la clase `CloudWatchLogger`:

```java
import io.github.jhoanhurtado.impl.CloudWatchLogger;
import io.github.jhoanhurtado.enums.AwsRegion;
import io.github.jhoanhurtado.enums.LogLevel;
import io.github.jhoanhurtado.interfaces.Logger;

public class Main {
    public static void main(String[] args) {
        Logger cloudLogger = new CloudWatchLogger(AwsRegion.US_EAST_1.getId(), "NombreDelGrupoDeLogs", "NombreDelFlujoDeLogs");
        cloudLogger.log("Mensaje de error", LogLevel.ERROR);
    }
}
```

#### Logger Compuesto

Para registrar mensajes en múltiples destinos simultáneamente, utiliza la clase `CompositeLogger`:

```java
import io.github.jhoanhurtado.impl.CompositeLogger;
import io.github.jhoanhurtado.impl.FileLogger;
import io.github.jhoanhurtado.impl.CloudWatchLogger;
import io.github.jhoanhurtado.enums.AwsRegion;
import io.github.jhoanhurtado.enums.LogLevel;
import io.github.jhoanhurtado.interfaces.Logger;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Logger fileLogger = new FileLogger("/ruta/al/archivo.log");
        Logger cloudLogger = new CloudWatchLogger(AwsRegion.US_EAST_1.getId(), "NombreDelGrupoDeLogs", "NombreDelFlujoDeLogs");
        Logger compositeLogger = new CompositeLogger(Arrays.asList(fileLogger, cloudLogger));

        compositeLogger.log("Mensaje crítico", LogLevel.CRITICAL);
    }
}
```

### Niveles de Log

La librería soporta los siguientes niveles de log, definidos en el enum `LogLevel`:

- `DEBUG`: Información detallada utilizada principalmente para diagnóstico y depuración.
- `INFO`: Mensajes informativos sobre el estado general de la aplicación.
- `WARN`: Advertencias sobre posibles problemas que no interrumpen la ejecución.
- `ERROR`: Errores que pueden afectar el funcionamiento pero permiten continuar la ejecución.
- `CRITICAL`: Fallos graves que pueden llevar a la terminación de la aplicación.

### Parámetros

#### FileLogger

- `pathFile`: Ruta completa del archivo de registro donde se guardarán los logs generados por el logger.

#### CloudWatchLogger

- `region`: La región de AWS donde se encuentran los recursos de CloudWatch Logs.
- `logGroupName`: El nombre del grupo de logs en CloudWatch donde se enviarán los registros.
- `logStreamName`: El nombre del flujo de logs dentro del grupo, en el que se almacenarán los eventos de log.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](./LICENSE) para más detalles.
