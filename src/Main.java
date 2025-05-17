import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Conversor de Monedas");

        String monedaOrigen;
        do {
            System.out.print("Ingrese la moneda de origen (por ejemplo, USD): ");
            monedaOrigen = scanner.nextLine().trim().toUpperCase();
        } while (!esCodigoMonedaValido(monedaOrigen));

        String monedaDestino;
        do {
            System.out.print("Ingrese la moneda de destino (por ejemplo, EUR): ");
            monedaDestino = scanner.nextLine().trim().toUpperCase();
        } while (!esCodigoMonedaValido(monedaDestino));

        double monto = 0;
        boolean montoValido = false;
        while (!montoValido) {
            System.out.print("Ingrese el monto que desea convertir: ");
            if (scanner.hasNextDouble()) {
                monto = scanner.nextDouble();
                if (monto > 0) {
                    montoValido = true;
                } else {
                    System.out.println("⚠️ El monto debe ser mayor que 0.");
                }
            } else {
                System.out.println("⚠️ Ingrese un número válido.");
                scanner.next();
            }
        }

        System.out.println("Datos ingresados:");
        System.out.println("Moneda origen: " + monedaOrigen);
        System.out.println("Moneda destino: " + monedaDestino);
        System.out.println("Monto: " + monto);

        scanner.close();
    }

    public static boolean esCodigoMonedaValido(String codigo) {
        if (codigo.length() != 3 || !codigo.matches("[A-Z]+")) {
            System.out.println("Código de moneda inválido. Debe tener 3 letras (ej. USD, EUR).");
            return false;
        }
        return true;
    }
}
