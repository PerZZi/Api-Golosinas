package Api.Golosinas.demo.entidades;

import jakarta.persistence.*;

@Entity
public class Golosina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreGolosina;
    private double precio;
    private String descripcion;
    private int Stock;

    public Golosina() {
    }

    public Golosina(String nombreGolosina, double precio, String descripcion, int stock) {
        this.nombreGolosina = nombreGolosina;
        this.precio = precio;
        this.descripcion = descripcion;
        Stock = stock;
    }

    public Long getId() {
        return id;
    }

    public String getNombreGolosina() {
        return nombreGolosina;
    }

    public void setNombreGolosina(String nombreGolosina) {
        this.nombreGolosina = nombreGolosina;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    @Override
    public String toString() {
        return "Golosina{" +
                ", nombreGolosina='" + nombreGolosina + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", Stock=" + Stock +
                '}';
    }
}
