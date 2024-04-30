package Api.Golosinas.demo.Repositories;

import Api.Golosinas.demo.entidades.PedidoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoUsuarioRespotory extends JpaRepository<PedidoUsuario, Long> {
}
