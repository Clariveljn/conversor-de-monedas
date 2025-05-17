import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiClient {
    private final String apiKey;
    private final HttpClient client;
    private final Gson gson;
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ApiClient(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Map<String, String> obtenerMonedas() throws IOException, InterruptedException {
        String url = BASE_URL + apiKey + "/codes";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Obtenemos el InputStream directamente
        HttpResponse<InputStream> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofInputStream()
        );

        if (response.statusCode() != 200) {
            throw new IOException("Error al obtener monedas: código " + response.statusCode());
        }

        // Leemos y parseamos el JSON
        try (InputStreamReader reader = new InputStreamReader(response.body())) {
            MonedasResponse data = gson.fromJson(reader, MonedasResponse.class);
            return data.getSupportedCodes().stream()
                    .collect(Collectors.toMap(
                            pair -> pair.get(0),
                            pair -> pair.get(1)
                    ));
        }
    }

    public double realizarConversion(String from, String to, double amount) throws IOException, InterruptedException {
        String url = String.format(Locale.US, BASE_URL + "%s/pair/%s/%s/%.2f", apiKey, from, to, amount);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("Error al realizar la conversión: código " + response.statusCode());
        }

        // Parseamos a un Map genérico para extraer conversion_result
        Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> data = gson.fromJson(response.body(), mapType);

        if (!"success".equals(data.get("result"))) {
            throw new IOException("Conversión fallida: " + data.get("error-type"));
        }

        return ((Number) data.get("conversion_result")).doubleValue();
    }
}

