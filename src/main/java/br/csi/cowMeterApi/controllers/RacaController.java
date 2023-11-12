package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.RacaDto;
import br.csi.cowMeterApi.models.Raca;
import br.csi.cowMeterApi.services.RacaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/raca")
public class RacaController {
    private final RacaService racaService;

    public RacaController(RacaService racaService) {
        this.racaService = racaService;
    }

    @PostMapping("/saveRaca")
    public ResponseEntity<Raca> salvarRaca(@RequestBody RacaDto racaDto) {
        Raca savedRaca = racaService.salvarRaca(racaDto);
        return new ResponseEntity<>(savedRaca, HttpStatus.OK);
    }

    @PutMapping("/updateRaca/{id}")
    public ResponseEntity<Raca> atualizarRaca(
            @RequestBody RacaDto racaDto,
            @PathVariable Long id
    ) {
        Raca updatedRaca = racaService.atualizarRaca(id, racaDto);
        return new ResponseEntity<>(updatedRaca, HttpStatus.OK);
    }

    @DeleteMapping("/deleteRaca/{id}")
    public ResponseEntity<?> deletarRaca(@PathVariable Long id) {
        try {
            if (racaService.verificarExistencia(id)) {
                boolean deleted = racaService.deletarRaca(id);
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
    public ResponseEntity<Raca> getRaca(@PathVariable Long id) {
        try {
            Raca raca = racaService.buscarRaca(id);
            return new ResponseEntity<>(raca, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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