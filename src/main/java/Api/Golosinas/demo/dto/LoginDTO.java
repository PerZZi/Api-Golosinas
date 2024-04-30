package Api.Golosinas.demo.dto;

import Api.Golosinas.demo.entidades.enums.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LoginDTO {

    private String email;
    private String password;

    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
