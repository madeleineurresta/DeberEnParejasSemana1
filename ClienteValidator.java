/**
 * Clase con responsabilidad única: validar datos de cliente
 * Elimina duplicación (DRY) en GestorPedidos
 */
public class ClienteValidator {

    public boolean validar(String nombre, String email) {
        return validarNombre(nombre) && validarEmail(email);
    }

    private boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error: nombre de cliente invalido");
            return false;
        }
        return true;
    }

    private boolean validarEmail(String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Error: email invalido");
            return false;
        }
        return true;
    }
}