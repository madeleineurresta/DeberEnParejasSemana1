import java.sql.*;
import java.util.List;

public class GestorPedidos {

    private Connection conexionBD;
    private ClienteValidator validator;
    private DescuentoStrategyFactory factory;

    public GestorPedidos() {
        try {
            this.conexionBD = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tienda", "root", "");
            this.validator = new ClienteValidator();
            this.factory = new DescuentoStrategyFactory();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void procesarPedido(String nombreCliente, String emailCliente,
                               List<String> nombresProductos,
                               List<Double> preciosProductos,
                               List<Integer> cantidades,
                               String tipoCliente) {

        if (!validator.validar(nombreCliente, emailCliente)) {
            return;
        }

        double subtotal = calcularSubtotal(nombresProductos, preciosProductos, cantidades);
        DescuentoStrategy strategy = factory.getStrategy(tipoCliente);
        double descuento = strategy.calcularDescuento(subtotal);
        double impuesto = (subtotal - descuento) * 0.12;
        double total = subtotal - descuento + impuesto;

        guardarEnBD(nombreCliente, total);
        generarFactura(nombreCliente, emailCliente, nombresProductos,
                      cantidades, preciosProductos, subtotal, descuento, impuesto, total);
        enviarEmail(emailCliente, nombreCliente, total);

        System.out.println("[LOG] Pedido procesado para " + nombreCliente + " - Total: $" + total);
    }

    public void cancelarPedido(String nombreCliente, String emailCliente, int idPedido) {
        if (!validator.validar(nombreCliente, emailCliente)) {
            return;
        }

        cancelarEnBD(idPedido);
        enviarEmailCancelacion(emailCliente, nombreCliente, idPedido);
    }

    private double calcularSubtotal(List<String> nombres, List<Double> precios, List<Integer> cantidades) {
        double subtotal = 0;
        for (int i = 0; i < nombres.size(); i++) {
            subtotal += precios.get(i) * cantidades.get(i);
        }
        return subtotal;
    }

    private void guardarEnBD(String nombreCliente, double total) {
        try {
            Statement stmt = conexionBD.createStatement();
            String sql = "INSERT INTO pedidos (cliente, total) VALUES ('"
                + nombreCliente + "', " + total + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al guardar el pedido: " + e.getMessage());
        }
    }

    private void cancelarEnBD(int idPedido) {
        try {
            Statement stmt = conexionBD.createStatement();
            String sql = "DELETE FROM pedidos WHERE id = " + idPedido;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error al cancelar el pedido: " + e.getMessage());
        }
    }

    private void generarFactura(String nombre, String email, List<String> nombres,
                                List<Integer> cantidades, List<Double> precios,
                                double subtotal, double descuento, double impuesto, double total) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("factura_" + nombre + ".txt");
            writer.write("FACTURA\n");
            writer.write("Cliente: " + nombre + "\n");
            writer.write("Email: " + email + "\n");
            for (int i = 0; i < nombres.size(); i++) {
                writer.write(nombres.get(i) + " x" + cantidades.get(i)
                    + " = $" + (precios.get(i) * cantidades.get(i)) + "\n");
            }
            writer.write("Subtotal: $" + subtotal + "\n");
            writer.write("Descuento: $" + descuento + "\n");
            writer.write("Impuesto: $" + impuesto + "\n");
            writer.write("TOTAL: $" + total + "\n");
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("Error al generar la factura: " + e.getMessage());
        }
    }

    private void enviarEmail(String email, String nombre, double total) {
        System.out.println("Enviando correo a " + email + "...");
        System.out.println("Asunto: Confirmacion de pedido");
        System.out.println("Cuerpo: Estimado " + nombre + ", su pedido por $" + total + " ha sido procesado.");
    }

    private void enviarEmailCancelacion(String email, String nombre, int id) {
        System.out.println("Enviando correo a " + email + "...");
        System.out.println("Asunto: Cancelacion de pedido");
        System.out.println("Cuerpo: Estimado " + nombre + ", su pedido #" + id + " ha sido cancelado.");
    }
}
