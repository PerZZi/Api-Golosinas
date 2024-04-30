package Api.Golosinas.demo.controller;

import Api.Golosinas.demo.config.JWT.JwtService;
import Api.Golosinas.demo.entidades.Usuario;
import Api.Golosinas.demo.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Usuario usuario = usuarioService.findByEmail(email);

        if (usuario == null || !usuario.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        String token = jwtService.generateToken(email, usuario.getRolType().name());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
