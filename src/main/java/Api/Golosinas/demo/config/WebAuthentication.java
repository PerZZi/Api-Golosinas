package Api.Golosinas.demo.config;

import Api.Golosinas.demo.Repositories.UsuarioRepository;
import Api.Golosinas.demo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName -> {
            Usuario usuario = usuarioRepository.findByEmail(inputName);
            if (usuario != null) {
                return new User(
                        usuario.getEmail(),
                        usuario.getPassword(),
                        AuthorityUtils.createAuthorityList("ROLE_" + usuario.getRolType().name())
                );
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado: " + inputName);
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
