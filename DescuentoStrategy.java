/**
 * Interfaz para estrategias de descuento
 * Permite agregar nuevos tipos sin modificar código existente (OCP)
 */
public interface DescuentoStrategy {
    double calcularDescuento(double subtotal);
}