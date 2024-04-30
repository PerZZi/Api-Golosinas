package Api.Golosinas.demo.Repositories;

import Api.Golosinas.demo.entidades.Golosina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GolosinaRepository extends JpaRepository<Golosina, Long> {
}
