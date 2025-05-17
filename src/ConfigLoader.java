import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final String CONFIG_FILE = "config.properties";

    public static String obtenerApiKey() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            return properties.getProperty("api.key").trim();
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de configuración.");
            return null;
        }
    }
}

