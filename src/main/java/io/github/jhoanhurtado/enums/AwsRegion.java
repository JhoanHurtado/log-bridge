package io.github.jhoanhurtado.enums;

/**
 * Enum que representa las diferentes regiones de AWS (Amazon Web Services).
 * 
 * Cada constante dentro de esta enumeración representa una región específica 
 * de AWS, identificada por su código de región. Además, algunas regiones se 
 * marcan como "globales", lo que indica que están disponibles de manera 
 * global y no están limitadas a una región geográfica específica.
 * 
 * <p>Las regiones globales incluyen:</p>
 * <ul>
 *   <li>{@link #AWS_GLOBAL}</li>
 *   <li>{@link #AWS_CN_GLOBAL}</li>
 *   <li>{@link #AWS_US_GOV_GLOBAL}</li>
 *   <li>{@link #AWS_ISO_GLOBAL}</li>
 *   <li>{@link #AWS_ISO_B_GLOBAL}</li>
 * </ul>
 * 
 * Las regiones no globales están representadas por códigos específicos, 
 * como {@link #US_WEST_1} o {@link #AP_SOUTH_1}, que corresponden a regiones 
 * geográficas específicas dentro de AWS.
 * 
 * <p>Este enum proporciona métodos para obtener el código de región y determinar 
 * si una región es global, así como un método para buscar una región utilizando 
 * su código de identificación.</p>
 */
public enum AwsRegion {
    AP_SOUTH_2("ap-south-2"),
    AP_SOUTH_1("ap-south-1"),
    EU_SOUTH_1("eu-south-1"),
    EU_SOUTH_2("eu-south-2"),
    US_GOV_EAST_1("us-gov-east-1"),
    ME_CENTRAL_1("me-central-1"),
    CA_CENTRAL_1("ca-central-1"),
    EU_CENTRAL_1("eu-central-1"),
    US_ISO_WEST_1("us-iso-west-1"),
    EU_CENTRAL_2("eu-central-2"),
    US_WEST_1("us-west-1"),
    US_WEST_2("us-west-2"),
    AF_SOUTH_1("af-south-1"),
    EU_NORTH_1("eu-north-1"),
    EU_WEST_3("eu-west-3"),
    EU_WEST_2("eu-west-2"),
    EU_WEST_1("eu-west-1"),
    AP_NORTHEAST_3("ap-northeast-3"),
    AP_NORTHEAST_2("ap-northeast-2"),
    AP_NORTHEAST_1("ap-northeast-1"),
    ME_SOUTH_1("me-south-1"),
    SA_EAST_1("sa-east-1"),
    AP_EAST_1("ap-east-1"),
    CN_NORTH_1("cn-north-1"),
    US_GOV_WEST_1("us-gov-west-1"),
    AP_SOUTHEAST_1("ap-southeast-1"),
    AP_SOUTHEAST_2("ap-southeast-2"),
    US_ISO_EAST_1("us-iso-east-1"),
    AP_SOUTHEAST_3("ap-southeast-3"),
    AP_SOUTHEAST_4("ap-southeast-4"),
    US_EAST_1("us-east-1"),
    US_EAST_2("us-east-2"),
    CN_NORTHWEST_1("cn-northwest-1"),
    US_ISOB_EAST_1("us-isob-east-1"),
    AWS_GLOBAL("aws-global", true),
    AWS_CN_GLOBAL("aws-cn-global", true),
    AWS_US_GOV_GLOBAL("aws-us-gov-global", true),
    AWS_ISO_GLOBAL("aws-iso-global", true),
    AWS_ISO_B_GLOBAL("aws-iso-b-global", true);

    private final String id;
    private final boolean isGlobalRegion;

    /**
     * Constructor para crear una instancia de {@code AwsRegion}.
     * 
     * Este constructor se utiliza para regiones no globales. Establece el ID 
     * de la región y marca la región como no global.
     *
     * @param id El código de la región en formato de cadena.
     */
    AwsRegion(String id) {
        this(id, false);
    }

    /**
     * Constructor para crear una instancia de {@code AwsRegion} con la opción 
     * de indicar si la región es global.
     * 
     * Este constructor es utilizado para las regiones globales y no globales. 
     * Establece el ID de la región y la marca global si es necesario.
     *
     * @param id El código de la región en formato de cadena.
     * @param isGlobalRegion Si la región es global.
     */
    AwsRegion(String id, boolean isGlobalRegion) {
        this.id = id;
        this.isGlobalRegion = isGlobalRegion;
    }

    /**
     * Obtiene el código de la región asociado con esta instancia de {@code AwsRegion}.
     *
     * @return El código de la región, como una cadena.
     */
    public String getId() {
        return id;
    }

    /**
     * Verifica si esta región es una región global.
     * 
     * Las regiones globales son aquellas que están disponibles globalmente 
     * y no están restringidas a una ubicación geográfica específica.
     *
     * @return {@code true} si la región es global, de lo contrario {@code false}.
     */
    public boolean isGlobalRegion() {
        return isGlobalRegion;
    }

    /**
     * Convierte una cadena en una instancia de {@code AwsRegion}.
     * 
     * Este método permite buscar una región utilizando su ID. Si no se encuentra 
     * una región con el ID proporcionado, se lanza una excepción {@link IllegalArgumentException}.
     *
     * @param regionId El código de la región en formato de cadena.
     * @return La instancia de {@code AwsRegion} correspondiente al ID proporcionado.
     * @throws IllegalArgumentException Si no se encuentra una región con el ID proporcionado.
     */
    public static AwsRegion fromString(String regionId) {
        for (AwsRegion region : AwsRegion.values()) {
            if (region.id.equalsIgnoreCase(regionId)) {
                return region;
            }
        }
        throw new IllegalArgumentException("No enum constant with id " + regionId);
    }

    /**
     * Obtiene el código de la región como una cadena desde la instancia de {@code AwsRegion}.
     * 
     * Este método es útil cuando se desea obtener el código de la región directamente desde
     * una instancia de {@code AwsRegion}.
     *
     * @param region La instancia de {@code AwsRegion}.
     * @return El código de la región como una cadena.
     */
    public static String getRegionString(AwsRegion region) {
        return region.getId();
    }
}