package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.RacaDto;
import br.csi.cowMeterApi.models.Raca;
import br.csi.cowMeterApi.services.RacaService;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.bridge.MessageUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cowMeterApi/raca")
public class RacaController {
    private final RacaService racaService;

    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @PostMapping("/saveRaca")
    public ResponseEntity<Raca> salvarRaca(@RequestBody RacaDto racaDto) {
        if (!isValidDto(racaDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Raca raca = new Raca();
        raca.setNome(racaDto.nome());
        raca.setDescricao(racaDto.descricao());

        Raca savedRaca = racaService.salvarRaca(raca);
        return new ResponseEntity<>(savedRaca, HttpStatus.OK);
    }


    @PutMapping("/updateRaca/{id}")
    public ResponseEntity<Raca> atualizarRaca(
            @RequestBody RacaDto racaDto,
            @PathVariable Long id
    ) {
        if (!isValidDto(racaDto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Raca raca = new Raca();
        raca.setNome(racaDto.nome());
        raca.setDescricao(racaDto.descricao());

        Raca updatedRaca;
        try {
            updatedRaca = racaService.atualizarRaca(id, raca);
            return new ResponseEntity<>(updatedRaca, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteRaca/{id}")
    public ResponseEntity<?> excluirRaca(@PathVariable Long id) {
        try {
            if (racaService.verificarExistencia(id)) {
                boolean deleted = racaService.excluirRaca(id);
                if (deleted) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Não foi possível excluir a raça. Ela está sendo referenciada por um bovino.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("A raça com o ID especificado não foi encontrada.", HttpStatus.NOT_FOUND);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Não é possível excluir a raça, pois está sendo referenciada por um bovino.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getRaca/{id}")
    public ResponseEntity<Object> getRaca(@PathVariable Long id) {
        Raca raca;
        try {
            raca = racaService.buscarRaca(id);
            return new ResponseEntity<>(raca, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            String mensagemDeErro = "Raça não encontrada.";
            return new ResponseEntity<>(mensagemDeErro, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getAllRacas")
    public ResponseEntity<List<Raca>> listarRacas() {
        List<Raca> racas = racaService.listarRacas();
        return new ResponseEntity<>(racas, HttpStatus.OK);
    }

    public boolean isValidDto(RacaDto racaDto) {
        return racaDto.nome() != null && !racaDto.nome().isBlank()
        && racaDto.descricao() != null;
    }
}
