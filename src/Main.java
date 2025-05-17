import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String apiKey = ConfigLoader.obtenerApiKey();
        if (apiKey == null || apiKey.isBlank()) {
            System.out.println("API Key no encontrada. Asegúrese de tener config.properties con 'api.key=TU_API_KEY'");
            return;
        }

        ApiClient apiClient = new ApiClient(apiKey);
        Scanner scanner = new Scanner(System.in);

        Map<String, String> monedasDisponibles;
        try {
            monedasDisponibles = apiClient.obtenerMonedas();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener las monedas: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ver monedas disponibles");
            System.out.println("2. Realizar conversión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.println("Monedas disponibles:");
                    monedasDisponibles.forEach((codigo, nombre) ->
                            System.out.println(codigo + " - " + nombre)
                    );
                    break;

                case "2":
                    System.out.print("Ingrese moneda de origen (ej. USD): ");
                    String monedaOrigen = leerMonedaValida(scanner, monedasDisponibles);

                    System.out.print("Ingrese moneda de destino (ej. EUR): ");
                    String monedaDestino = leerMonedaValida(scanner, monedasDisponibles);

                    double monto = leerMonto(scanner);

                    try {
                        double resultado = apiClient.realizarConversion(monedaOrigen, monedaDestino, monto);
                        System.out.printf("%.2f %s equivalen a %.2f %s%n",
                                monto, monedaOrigen, resultado, monedaDestino);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Error al realizar la conversión: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.println("¡Gracias por usar el conversor de monedas!");
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static String leerMonedaValida(Scanner scanner, Map<String, String> monedasDisponibles) {
        String codigo;
        while (true) {
            codigo = scanner.nextLine().trim().toUpperCase();
            if (monedasDisponibles.containsKey(codigo)) {
                return codigo;
            }
            System.out.print("Código inválido. Ingrese un código válido: ");
        }
    }

    private static double leerMonto(Scanner scanner) {
        while (true) {
            System.out.print("Ingrese el monto a convertir: ");
            if (scanner.hasNextDouble()) {
                double monto = scanner.nextDouble();
                scanner.nextLine();
                if (monto > 0) return monto;
                else System.out.println("El monto debe ser mayor que 0.");
            } else {
                System.out.println("Ingrese un número válido.");
                scanner.nextLine();
            }
        }
    }
}

