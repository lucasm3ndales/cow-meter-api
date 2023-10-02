package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.models.Raca;
import br.csi.cowMeterApi.repositories.RacaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RacaService {
    private final RacaRepository racaRepository;

    public RacaService(RacaRepository racaRepository) {
        this.racaRepository = racaRepository;
    }

    public Raca salvarRaca(Raca raca) {
        return racaRepository.save(raca);
    }

    public Raca atualizarRaca(Long id, Raca raca) {
        Optional<Raca> racaOptional = racaRepository.findById(id);
        if (racaOptional.isPresent()) {
            raca.setId(id);
            return racaRepository.save(raca);
        } else {
            throw new EntityNotFoundException("Raça não encontrada com o ID: " + id);
        }
    }

    public boolean verificarExistencia(Long id) {
        return racaRepository.existsById(id);
    }


    public boolean excluirRaca(Long id) {
        Optional<Raca> racaOptional = racaRepository.findById(id);
        if (racaOptional.isPresent()) {
            racaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Raca buscarRaca(Long id) {
        return racaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raça não encontrada com o ID: " + id));
    }

    public List<Raca> listarRacas() {
        return racaRepository.findAll();
    }
}
