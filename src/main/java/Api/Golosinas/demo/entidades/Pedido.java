package Api.Golosinas.demo.entidades;

import Api.Golosinas.demo.entidades.enums.EstadoPedido;
import Api.Golosinas.demo.entidades.enums.Rol;
import Api.Golosinas.demo.entidades.enums.TipoPedido;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPedido tipoPedido;
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List <PedidoUsuario> pedidoUsuario = new ArrayList<>();
    @OneToMany
    private List<Golosina> listaGolosinas=new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
    private LocalDate fechaCreaccionPedido;
    private LocalDate fechaAprobacion;
    private LocalDate fechaRechazo;


    public Pedido() {
    }

    public Pedido(TipoPedido tipoPedido, List<PedidoUsuario> pedidoUsuario, List<Golosina> listaGolosinas, EstadoPedido estadoPedido, LocalDate fechaCreaccionPedido) {
        this.tipoPedido = tipoPedido;
        this.pedidoUsuario = pedidoUsuario;
        this.listaGolosinas = listaGolosinas;
        this.estadoPedido = estadoPedido;
        this.fechaCreaccionPedido = fechaCreaccionPedido;
    }

    public Long getId() {
        return id;
    }

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public List<PedidoUsuario> getPedidoUsuario() {
        return pedidoUsuario;
    }

    public void setPedidoUsuario(List<PedidoUsuario> pedidoUsuario) {
        this.pedidoUsuario = pedidoUsuario;
    }

    public List<Golosina> getListaGolosinas() {
        return listaGolosinas;
    }

    public void setListaGolosinas(List<Golosina> listaGolosinas) {
        this.listaGolosinas = listaGolosinas;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public LocalDate getFechaCreaccionPedido() {
        return fechaCreaccionPedido;
    }

    public void setFechaCreaccionPedido(LocalDate fechaCreaccionPedido) {
        this.fechaCreaccionPedido = fechaCreaccionPedido;
    }

    public LocalDate getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDate fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public LocalDate getFechaRechazo() {
        return fechaRechazo;
    }

    public void setFechaRechazo(LocalDate fechaRechazo) {
        this.fechaRechazo = fechaRechazo;
    }

    public void agregarGolosina(Golosina golosina) {
        this.listaGolosinas.add(golosina);
    }

    public void eliminarGolosina(Golosina golosina) {
        this.listaGolosinas.remove(golosina);
    }

    public void enviarAprobacion() {
        if (this.estadoPedido == EstadoPedido.BORRADOR) {
            this.estadoPedido = EstadoPedido.PENDIENTE;
        } else {
            throw new IllegalStateException("Solo un pedido en Borrador puede ser enviado para aprobación.");
        }
    }

    public void aprobar(Usuario usuario) {
        if (this.estadoPedido == EstadoPedido.PENDIENTE) {
            this.estadoPedido = EstadoPedido.APROBADO;
            this.fechaAprobacion = LocalDate.now();
        } else {
            throw new IllegalStateException("Solo un pedido pendiente de aprobación puede ser rechazado.");
        }
    }

    public void rechazar() {
        if (this.estadoPedido == EstadoPedido.PENDIENTE) {
            this.estadoPedido = EstadoPedido.RECHAZADO;
            this.fechaAprobacion = LocalDate.now();
        } else {
            throw new IllegalStateException("Solo un pedido pendiente de aprobación puede ser rechazado.");
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", tipoPedido=" + tipoPedido +
                ", listaGolosinas=" + listaGolosinas +
                ", estadoPedido=" + estadoPedido +
                ", fechaCreaccionPedido=" + fechaCreaccionPedido +
                ", fechaAprobacion=" + fechaAprobacion +
                ", fechaRechazo=" + fechaRechazo +
                '}';
    }
}
