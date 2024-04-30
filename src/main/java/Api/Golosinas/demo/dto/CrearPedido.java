package Api.Golosinas.demo.dto;

import Api.Golosinas.demo.entidades.Golosina;
import Api.Golosinas.demo.entidades.Pedido;
import Api.Golosinas.demo.entidades.enums.TipoPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrearPedido {

    private TipoPedido tipoPedido;
    private Long usuarioId;
    private List<Golosina> listaGolosinas = new ArrayList<>();

    public CrearPedido() {
    }

    // Getters y setters
    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Golosina> getListaGolosinas() {
        return listaGolosinas;
    }

    public void setListaGolosinas(List<Golosina> listaGolosinas) {
        this.listaGolosinas = listaGolosinas;
    }

    // Método para agregar una golosina a la lista
    public void agregarGolosina(Golosina golosina) {
        this.listaGolosinas.add(golosina);
    }
}
