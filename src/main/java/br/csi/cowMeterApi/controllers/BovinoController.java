package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.BovinoDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import br.csi.cowMeterApi.models.Bovino;
import br.csi.cowMeterApi.services.BovinoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cowMeterApi/bovino")
public class BovinoController {
    private final BovinoService bovinoService;

    public BovinoController(BovinoService bovinoService) {
        this.bovinoService = bovinoService;
    }

    @PostMapping("/saveBovino")
    public ResponseEntity<String> salvarBovino(@RequestBody BovinoDto bovinoDto) throws Exception {
        if (isValidDto(bovinoDto)) {
            Bovino bovino = bovinoService.salvarBovino(bovinoDto);
            if (bovino != null) {
                return new ResponseEntity<>("Bovino salvo com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao salvar dados do bovino!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        throw new InvalidRequestDataException("Os dados enviados para salvar o bovino são inválidos!");
    }

    @PutMapping("/updateBovino/{id}")
    public ResponseEntity<String> atualizarBovino(
            @RequestBody BovinoDto bovinoDto,
            @PathVariable Long id
    ) throws Exception {
        if (isValidDto(bovinoDto)) {
            Bovino bovino = bovinoService.atualizarBovino(id, bovinoDto);
            if (bovino != null) {
                return new ResponseEntity<>("Bovino atualizado com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao atualizar dados do bovino!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        throw new InvalidRequestDataException("Os dados enviados para atualizar o bovino são inválidos!");
    }

    @DeleteMapping("/deleteBovino/{id}")
    public ResponseEntity<Boolean> deletarBovino(@PathVariable Long id) throws Exception {
        if (id != null) {
            boolean res = bovinoService.deletarBovino(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        throw new InvalidRequestDataException("Identificador do bovino não encontrado!");
    }

    @GetMapping("/getBovino/{id}")
    public ResponseEntity<Bovino> getBovino(@PathVariable Long id) throws Exception {
        if (id != null) {
            Bovino res = bovinoService.getBovino(id);
            if (res != null) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            throw new EntityNotFoundException("Bovino não encontrado!");
        }
        throw new InvalidRequestDataException("Identificador do bovino não encontrado!");
    }

    @GetMapping("/getAllBovinos")
    public ResponseEntity<Page<Bovino>> getAllBovinos(@RequestParam Pageable pageable) {
        if (pageable != null) {
            Page<Bovino> bovinos = bovinoService.getAllBovinos(pageable);
            return new ResponseEntity<>(bovinos, HttpStatus.OK);
        }
        Page<Bovino> bovinos = bovinoService.getAllBovinos(PageRequest.of(0, 10, Sort.unsorted()));
        return new ResponseEntity<>(bovinos, HttpStatus.OK);
    }

    private boolean isValidDto(BovinoDto bovinoDto) {
        return bovinoDto != null &&
                bovinoDto.idRaca() != null &&
                bovinoDto.nome() != null && !bovinoDto.nome().isBlank() &&
                bovinoDto.peso() != null &&
                bovinoDto.dataNasc() != null &&
                bovinoDto.sexo() != null &&
                bovinoDto.dataEntradaCio() != null &&
                bovinoDto.tipoBovino() != null;
    }
}