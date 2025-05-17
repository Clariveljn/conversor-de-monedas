import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al Conversor de Monedas");

        System.out.print("Ingrese la moneda de origen (por ejemplo, USD): ");
        String monedaOrigen = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese la moneda de destino (por ejemplo, CLP): ");
        String monedaDestino = scanner.nextLine().toUpperCase();

        System.out.print("Ingrese el monto que desea convertir: ");
        double monto = scanner.nextDouble();

        System.out.println("🧾 Datos ingresados:");
        System.out.println("Moneda origen: " + monedaOrigen);
        System.out.println("Moneda destino: " + monedaDestino);
        System.out.println("Monto: " + monto);

        scanner.close();
    }
}
