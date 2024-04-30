package Api.Golosinas.demo.dto;

import Api.Golosinas.demo.entidades.PedidoUsuario;
import Api.Golosinas.demo.entidades.enums.EstadoPedido;
import Api.Golosinas.demo.entidades.Pedido;
import Api.Golosinas.demo.entidades.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {

    private Long id;
    private List <PedidoUsuarioDTO> pedidoUsuario = new ArrayList<>();
    private List<GolosinaDTO> listaGolosinas=new ArrayList<>();
    private EstadoPedido estadoPedido;
    private LocalDate fechaCreaccionPedido;
    private LocalDate fechaAprobacion;
    private LocalDate fechaRechazo;

    public PedidoDTO(Pedido pedido) {
        id = pedido.getId();
        listaGolosinas = pedido.getListaGolosinas().stream().map(GolosinaDTO::new).collect(Collectors.toList());
        estadoPedido = pedido.getEstadoPedido();
        fechaCreaccionPedido = pedido.getFechaCreaccionPedido();
        fechaAprobacion = pedido.getFechaAprobacion();
        fechaRechazo = pedido.getFechaRechazo();
    }

    public Long getId() {
        return id;
    }

    public List<PedidoUsuarioDTO> getPedidoUsuario() {
        return pedidoUsuario;
    }

    public List<GolosinaDTO> getListaGolosinas() {
        return listaGolosinas;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public LocalDate getFechaCreaccionPedido() {
        return fechaCreaccionPedido;
    }

    public LocalDate getFechaAprobacion() {
        return fechaAprobacion;
    }

    public LocalDate getFechaRechazo() {
        return fechaRechazo;
    }
}
