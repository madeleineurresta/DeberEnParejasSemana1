import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GestorPedidos gestor = new GestorPedidos();

        List<String> productos = Arrays.asList("Laptop", "Mouse", "Teclado");
        List<Double> precios   = Arrays.asList(800.0, 25.0, 45.0);
        List<Integer> cantidades = Arrays.asList(1, 2, 1);

        System.out.println("===== CLIENTE VIP =====");
        gestor.procesarPedido("Carlos Perez", "carlos@email.com",
                productos, precios, cantidades, "VIP");

        System.out.println("\n===== CLIENTE FRECUENTE =====");
        gestor.procesarPedido("Maria Lopez", "maria@email.com",
                productos, precios, cantidades, "FRECUENTE");

        System.out.println("\n===== CLIENTE REGULAR =====");
        gestor.procesarPedido("Pedro Gomez", "pedro@email.com",
                productos, precios, cantidades, "REGULAR");

        System.out.println("\n===== CLIENTE NUEVO =====");
        gestor.procesarPedido("Ana Torres", "ana@email.com",
                productos, precios, cantidades, "NUEVO");

        System.out.println("\n===== CANCELAR PEDIDO =====");
        gestor.cancelarPedido("Carlos Perez", "carlos@email.com", 1);

        System.out.println("\n===== VALIDACION: nombre invalido =====");
        gestor.procesarPedido("", "valido@email.com",
                productos, precios, cantidades, "REGULAR");

        System.out.println("\n===== VALIDACION: email invalido =====");
        gestor.procesarPedido("Luis Vera", "correo-sin-arroba",
                productos, precios, cantidades, "NUEVO");
    }
}
