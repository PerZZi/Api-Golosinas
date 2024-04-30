package Api.Golosinas.demo.services.implement;

import Api.Golosinas.demo.Repositories.GolosinaRepository;
import Api.Golosinas.demo.dto.GolosinaDTO;
import Api.Golosinas.demo.entidades.Golosina;
import Api.Golosinas.demo.services.GolosinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class GolosinaServiceImplement implements GolosinaService {

    @Autowired
    private GolosinaRepository golosinaRepository;

    @Override
    public Golosina findById(Long id) {
        return golosinaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Golosina> getAllGolosina() {
        return golosinaRepository.findAll();
    }

    @Override
    public List<GolosinaDTO> getAllGolosinaDTO() {
        return getAllGolosina().stream().map(GolosinaDTO::new).collect(Collectors.toList());
    }

    @Override
    public void save(Golosina golosina) {
        golosinaRepository.save(golosina);
    }
}
