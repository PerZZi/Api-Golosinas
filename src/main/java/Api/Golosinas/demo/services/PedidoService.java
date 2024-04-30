package Api.Golosinas.demo.services;

import Api.Golosinas.demo.dto.CrearPedido;
import Api.Golosinas.demo.dto.PedidoDTO;
import Api.Golosinas.demo.entidades.Pedido;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {

    Pedido findById (Long id);
    List<Pedido> getAllPedidos();
    List<PedidoDTO> getAllPedidosDTO();
    Pedido creacionPedido(CrearPedido crearPedido);
    Pedido enviarAprobacion(Long id);
    Pedido aprobarPedido(Long id);
    Pedido rechazarPedido(Long id);

    void save(Pedido nuevoPedido);
}
