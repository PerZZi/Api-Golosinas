package Api.Golosinas.demo.config.JWT;

import Api.Golosinas.demo.Repositories.UsuarioRepository;
import Api.Golosinas.demo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override //Aca sobreescribiremos este metodo para que sea como nosotros queramos
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username); //Se le pasa un email y devuelve un client

        if (usuario == null){
            throw new UsernameNotFoundException(username);
        }

        return User   // usuario que se guardara en el contexHolder
                .withUsername(username) //email
                .password(usuario.getPassword()) // del cliente obtengo el password
                .roles("CLIENTE") // se le da el rol de client
                .build();
    }
}
