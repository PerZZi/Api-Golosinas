package Api.Golosinas.demo.services;

import Api.Golosinas.demo.dto.UsuarioDTO;
import Api.Golosinas.demo.entidades.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario findById (Long id);
    Usuario findByEmail (String email);
    List<Usuario> getAllUsuarios();
    List<UsuarioDTO> getAllUsuariosDTO();
    Usuario getAuthenticatedUser(String name);
}
