import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorPedidos gestor = new GestorPedidos();

        System.out.println("=== SISTEMA DE PEDIDOS ===");
        System.out.println("1. Procesar pedido");
        System.out.println("2. Cancelar pedido");
        System.out.print("Elige una opcion: ");
        int opcion = Integer.parseInt(sc.nextLine());

        if (opcion == 1) {
            System.out.print("Nombre del cliente: ");
            String nombre = sc.nextLine();

            System.out.print("Email del cliente: ");
            String email = sc.nextLine();

            System.out.print("Tipo de cliente (VIP / FRECUENTE / REGULAR / NUEVO): ");
            String tipo = sc.nextLine();

            System.out.print("Cuantos productos vas a ingresar? ");
            int cantidad = Integer.parseInt(sc.nextLine());

            List<String> nombres = new ArrayList<>();
            List<Double> precios = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();

            for (int i = 1; i <= cantidad; i++) {
                System.out.print("Nombre del producto " + i + ": ");
                nombres.add(sc.nextLine());

                System.out.print("Precio del producto " + i + ": ");
                precios.add(Double.parseDouble(sc.nextLine()));

                System.out.print("Cantidad del producto " + i + ": ");
                cantidades.add(Integer.parseInt(sc.nextLine()));
            }

            gestor.procesarPedido(nombre, email, nombres, precios, cantidades, tipo);

        } else if (opcion == 2) {
            System.out.print("Nombre del cliente: ");
            String nombre = sc.nextLine();

            System.out.print("Email del cliente: ");
            String email = sc.nextLine();

            System.out.print("ID del pedido a cancelar: ");
            int id = Integer.parseInt(sc.nextLine());

            gestor.cancelarPedido(nombre, email, id);
        } else {
            System.out.println("Opcion invalida.");
        }

        sc.close();
    }
}
