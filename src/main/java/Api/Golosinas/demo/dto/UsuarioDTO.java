package Api.Golosinas.demo.dto;

import Api.Golosinas.demo.entidades.enums.Rol;
import Api.Golosinas.demo.entidades.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private Rol RolType;

    public UsuarioDTO(Usuario usuario) {
        id = usuario.getId();
        nombre = usuario.getNombre();
        email = usuario.getEmail();
        RolType = usuario.getRolType();
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Rol getRolType() {
        return RolType;
    }
}
