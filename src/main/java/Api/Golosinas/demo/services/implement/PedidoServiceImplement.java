package Api.Golosinas.demo.services.implement;

import Api.Golosinas.demo.Repositories.GolosinaRepository;
import Api.Golosinas.demo.Repositories.PedidoRepository;
import Api.Golosinas.demo.Repositories.UsuarioRepository;
import Api.Golosinas.demo.dto.CrearPedido;
import Api.Golosinas.demo.dto.PedidoDTO;
import Api.Golosinas.demo.entidades.PedidoUsuario;
import Api.Golosinas.demo.entidades.enums.EstadoPedido;
import Api.Golosinas.demo.entidades.Golosina;
import Api.Golosinas.demo.entidades.Pedido;
import Api.Golosinas.demo.entidades.Usuario;
import Api.Golosinas.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PedidoServiceImplement implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private GolosinaRepository golosinaRepository;


    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<PedidoDTO> getAllPedidosDTO() {
        return getAllPedidos().stream().map(PedidoDTO::new).collect(Collectors.toList());
    }

    @Override
    public Pedido creacionPedido(CrearPedido crearPedido) {
        Usuario usuario = usuarioRepository.findById(crearPedido.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Golosina> golosinas = crearPedido.getListaGolosinas().stream()
                .map(golosinaDTO -> golosinaRepository.findById(golosinaDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Golosina no encontrada")))
                .collect(Collectors.toList());

        PedidoUsuario pedidoUsuario = new PedidoUsuario();
        pedidoUsuario.setUsuario(usuario);

        Pedido pedido = new Pedido();
        pedido.setTipoPedido(crearPedido.getTipoPedido());
        pedido.setListaGolosinas(golosinas);
        pedido.setEstadoPedido(EstadoPedido.BORRADOR);
        pedido.setFechaCreaccionPedido(LocalDate.now());

        List<PedidoUsuario> listaPedidoUsuario = new ArrayList<>();
        listaPedidoUsuario.add(pedidoUsuario);

        pedidoUsuario.setPedido(pedido);
        pedido.setPedidoUsuario(listaPedidoUsuario);

        pedidoRepository.save(pedido);

        return pedido;
    }

    @Override
    public Pedido enviarAprobacion(Long id) {
        Pedido pedido = findById(id);

        if (pedido.getEstadoPedido() != EstadoPedido.BORRADOR) {
            throw new RuntimeException("El pedido no está en estado Borrador");
        }

        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedidoRepository.save(pedido);

        return pedido;
    }

    @Override
    public Pedido aprobarPedido(Long id) {
        Pedido pedido = findById(id);

        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            throw new RuntimeException("El pedido no está pendiente de aprobación");
        }

        pedido.setEstadoPedido(EstadoPedido.APROBADO);
        pedidoRepository.save(pedido);

        return pedido;
    }

    @Override
    public Pedido rechazarPedido(Long id) {
        Pedido pedido = findById(id);

        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            throw new RuntimeException("El pedido no está pendiente de aprobación");
        }

        pedido.setEstadoPedido(EstadoPedido.RECHAZADO);
        pedidoRepository.save(pedido);

        return pedido;
    }

    @Override
    public void save(Pedido nuevoPedido) {
        pedidoRepository.save(nuevoPedido);
    }

}
