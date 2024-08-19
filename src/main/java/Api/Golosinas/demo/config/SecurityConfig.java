/*package Api.Golosinas.demo.config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Endpoints permitidos para todos
                        .requestMatchers("/my-project/index.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pedidos/todos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pedidos/crear").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/pedidos/enviar-aprobacion/*").permitAll()

                        // Endpoints permitidos solo para RESPONSABLES
                        .requestMatchers(HttpMethod.PUT, "/pedidos/aprobar/*").hasAuthority("RESPONSABLE")
                        .requestMatchers(HttpMethod.PUT, "/pedidos/rechazar/*").hasAuthority("RESPONSABLE")

                        // Rechazar cualquier otra solicitud
                        .anyRequest().denyAll()
                );

        http.csrf(csrf -> csrf.disable()); // Desactivar CSRF para simplicidad

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
                frameOptionsConfig -> frameOptionsConfig.disable()));

        http.exceptionHandling( exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> response.sendError(403)));

        http.formLogin(formLogin -> formLogin
                .loginPage("/my-project/index.html") // Página de inicio de sesión
                .loginProcessingUrl("/login") // URL para procesamiento de inicio de sesión
                .usernameParameter("email") // Parámetro para el nombre de usuario
                .passwordParameter("password") // Parámetro para la contraseña
                .successHandler((request, response, authentication) -> {
                    clearAuthenticationAttributes(request); // Limpiar atributos de autenticación
                })
                .failureHandler((request, response, exception) -> {
                    response.sendError(401, "Credenciales inválidas"); // Manejo de fallo de inicio de sesión
                })
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/api/logout") // Endpoint de logout
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID") // Elimina cookies al salir
        );

        // Configuración de excepción para denegar acceso no autorizado
        http.exceptionHandling(exceptionHandlingConfigurer ->
                exceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) ->
                        response.sendError(403, "No autorizado") // Manejo de acceso no autorizado
                )
        );


        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/", "http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}*/

