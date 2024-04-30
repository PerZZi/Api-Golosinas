package Api.Golosinas.demo.dto;

import Api.Golosinas.demo.entidades.Golosina;

public class GolosinaDTO {

    private Long id;
    private String nombreGolosina;
    private double precio;
    private String descripcion;
    private int Stock;

    public GolosinaDTO() {
    }

    public GolosinaDTO(Golosina golosina) {
        id = golosina.getId();
        nombreGolosina = golosina.getNombreGolosina();
        precio = golosina.getPrecio();
        descripcion = golosina.getDescripcion();
        Stock = golosina.getStock();
    }

    public Long getId() {
        return id;
    }

    public String getNombreGolosina() {
        return nombreGolosina;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return Stock;
    }
}
