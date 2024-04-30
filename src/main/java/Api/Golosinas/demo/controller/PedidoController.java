package Api.Golosinas.demo.controller;

import Api.Golosinas.demo.dto.CrearPedido;
import Api.Golosinas.demo.entidades.Golosina;
import Api.Golosinas.demo.entidades.Pedido;
import Api.Golosinas.demo.entidades.PedidoUsuario;
import Api.Golosinas.demo.entidades.Usuario;
import Api.Golosinas.demo.entidades.enums.EstadoPedido;
import Api.Golosinas.demo.services.GolosinaService;
import Api.Golosinas.demo.services.PedidoService;
import Api.Golosinas.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GolosinaService golosinaService;


    @PostMapping("/crear")
    public ResponseEntity<String> tomarPedido(@RequestBody CrearPedido crearPedido) {

        if (crearPedido.getTipoPedido() == null) {
            return new ResponseEntity<>("El tipo de pedido es obligatorio.", HttpStatus.FORBIDDEN);
        }

        if (crearPedido.getUsuarioId() == null) {
            return new ResponseEntity<>("El ID del usuario es obligatorio.", HttpStatus.FORBIDDEN);
        }

        if (crearPedido.getListaGolosinas() == null || crearPedido.getListaGolosinas().isEmpty()) {
            return new ResponseEntity<>("La lista de golosinas debe contener al menos una golosina.", HttpStatus.FORBIDDEN);
        }

        Usuario usuario = usuarioService.findById(crearPedido.getUsuarioId());
        if (usuario == null) {
            return new ResponseEntity<>("Usuario no encontrado.", HttpStatus.NOT_FOUND);
        }


        for (Golosina golosina : crearPedido.getListaGolosinas()) {
            golosinaService.save(golosina);
        }

        // Crear relación PedidoUsuario
        PedidoUsuario pedidoUsuario = new PedidoUsuario();
        pedidoUsuario.setUsuario(usuario);

        List<PedidoUsuario> listaPedidoUsuario = new ArrayList<>();
        listaPedidoUsuario.add(pedidoUsuario);

        // Crear el nuevo pedido con las golosinas guardadas
        Pedido nuevoPedido = new Pedido(
                crearPedido.getTipoPedido(),
                listaPedidoUsuario,
                crearPedido.getListaGolosinas(),
                EstadoPedido.BORRADOR, // Ejemplo de estado inicial
                LocalDate.now() // Fecha actual como fecha de creación
        );

        // Guardar el nuevo pedido
        pedidoService.save(nuevoPedido);

        nuevoPedido.getPedidoUsuario().add(pedidoUsuario);

        // Guardar nuevo pedido
        pedidoService.save(nuevoPedido);

        return new ResponseEntity<>("Nuevo pedido creado exitosamente.", HttpStatus.CREATED);
    }

    @PutMapping("/enviar-aprobacion/{id}")
    public ResponseEntity<Object> enviarAprobacion(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID del pedido es obligatorio.");
        }

        Pedido pedido = pedidoService.findById(id);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("El pedido con el ID " + id + " no existe.");
        }

        pedido.setEstadoPedido(EstadoPedido.PENDIENTE);
        pedidoService.save(pedido);


        return ResponseEntity.ok("pedido enviando");
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Pedido>> obtenerTodosPedidos() {
        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Pedido>> obtenerPedidosPorUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);

        List<Pedido> pedidos = usuario.getPedidos().stream()
                .map(PedidoUsuario::getPedido)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/aprobar/{id}")
    public ResponseEntity<Object> aprobarPedido(@PathVariable Long id) {
            Pedido pedido = pedidoService.findById(id);

            if (pedido == null) {
                return ResponseEntity.badRequest().body("Pedido no encontrado.");
            }

            if (pedido.getEstadoPedido().equals(EstadoPedido.APROBADO)) {
                return ResponseEntity.badRequest().body("El pedido ya está aprobado.");
            }

            if (pedido.getEstadoPedido().equals(EstadoPedido.RECHAZADO)) {
                return ResponseEntity.badRequest().body("El pedido está rechazado y no se puede aprobar.");
            }

            pedidoService.aprobarPedido(id);
            pedidoService.save(pedido);
        return ResponseEntity.ok("pedido aprobado");
    }

    @PutMapping("/rechazar/{id}")
    public ResponseEntity<Object> rechazarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.rechazarPedido(id);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("Pedido no encontrado.");
        }
        return ResponseEntity.ok(pedido);
    }
}
