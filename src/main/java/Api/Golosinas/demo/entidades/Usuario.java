package Api.Golosinas.demo.entidades;

import Api.Golosinas.demo.entidades.enums.Rol;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol RolType;
    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
    private List<PedidoUsuario> pedidos = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String nombre, String email,String password, Rol rolType) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        RolType = rolType;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRolType() {
        return RolType;
    }

    public void setRolType(Rol rolType) {
        RolType = rolType;
    }

    public List<PedidoUsuario> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoUsuario> pedidos) {
        this.pedidos = pedidos;
    }

    public void agregarPedidoUsuario(PedidoUsuario pedidos) {
        pedidos.setUsuario(this);
        this.pedidos.add(pedidos);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", RolType=" + RolType +
                '}';
    }
}
