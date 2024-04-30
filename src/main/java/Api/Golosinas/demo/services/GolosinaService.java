package Api.Golosinas.demo.services;

import Api.Golosinas.demo.dto.GolosinaDTO;
import Api.Golosinas.demo.entidades.Golosina;

import java.util.List;

public interface GolosinaService {

    Golosina findById (Long id);
    List<Golosina> getAllGolosina();
    List<GolosinaDTO> getAllGolosinaDTO();

    void save(Golosina golosina);
}
