package Api.Golosinas.demo.dto;

import Api.Golosinas.demo.entidades.Pedido;
import Api.Golosinas.demo.entidades.PedidoUsuario;
import Api.Golosinas.demo.entidades.Usuario;
import jakarta.persistence.ManyToOne;

public class PedidoUsuarioDTO {

    private Long id;
    private Usuario usuario;
    private Pedido pedido;

    public PedidoUsuarioDTO(PedidoUsuario pedidoUsuario) {
        id = pedidoUsuario.getId();
        usuario = pedidoUsuario.getUsuario();
        pedido = pedidoUsuario.getPedido();
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
