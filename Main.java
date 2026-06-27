import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear instancia de GestorPedidos
            GestorPedidos gestor = new GestorPedidos();

            // Datos de prueba
            String nombre = "Juan Perez";
            String email = "juan@email.com";
            List<String> productos = Arrays.asList("Laptop", "Mouse", "Teclado");
            List<Double> precios = Arrays.asList(800.0, 25.0, 45.0);
            List<Integer> cantidades = Arrays.asList(1, 2, 1);
            String tipoCliente = "VIP";

            // Procesar pedido
            System.out.println("=== PROCESANDO PEDIDO ===\n");
            gestor.procesarPedido(nombre, email, productos, precios, cantidades, tipoCliente);

            // Cancelar pedido
            System.out.println("\n=== CANCELANDO PEDIDO ===\n");
            gestor.cancelarPedido(nombre, email, 1);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}