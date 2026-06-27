package main.model;

import java.util.List;

/**
 * Modelo de datos para un pedido
 * Encapsula toda la información relacionada con un pedido
 */
public class Pedido {
    private final String nombreCliente;
    private final String emailCliente;
    private final List<String> nombresProductos;
    private final List<Double> preciosProductos;
    private final List<Integer> cantidades;
    private int id;

    public Pedido(String nombreCliente, String emailCliente,
                  List<String> nombresProductos, List<Double> preciosProductos,
                  List<Integer> cantidades) {
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
        this.nombresProductos = nombresProductos;
        this.preciosProductos = preciosProductos;
        this.cantidades = cantidades;
    }
    
    public String getNombreCliente() { return nombreCliente; }
    public String getEmailCliente() { return emailCliente; }
    public List<String> getNombresProductos() { return nombresProductos; }
    public List<Double> getPreciosProductos() { return preciosProductos; }
    public List<Integer> getCantidades() { return cantidades; }
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}