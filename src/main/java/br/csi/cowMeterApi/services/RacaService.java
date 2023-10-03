package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.dtos.RacaDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
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

    public Raca salvarRaca(RacaDto racaDto) {
        Raca raca = new Raca();
        raca.setNome(racaDto.nome());
        raca.setDescricao(racaDto.descricao());

        return racaRepository.save(raca);
    }

    public Raca atualizarRaca(Long id, RacaDto racaDto) {
        Raca raca = racaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Raça não encontrada com o ID: " + id));

        raca.setNome(racaDto.nome());
        raca.setDescricao(racaDto.descricao());

        return racaRepository.save(raca);
    }

    public boolean verificarExistencia(Long id) {
        return racaRepository.existsById(id);
    }


    public boolean deletarRaca(Long id) {
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