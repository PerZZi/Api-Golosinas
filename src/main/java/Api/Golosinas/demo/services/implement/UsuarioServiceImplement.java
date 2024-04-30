package Api.Golosinas.demo.services.implement;

import Api.Golosinas.demo.Repositories.UsuarioRepository;
import Api.Golosinas.demo.dto.UsuarioDTO;
import Api.Golosinas.demo.entidades.Usuario;
import Api.Golosinas.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UsuarioServiceImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<UsuarioDTO> getAllUsuariosDTO() {
        return getAllUsuarios().stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    @Override
    public Usuario getAuthenticatedUser(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
