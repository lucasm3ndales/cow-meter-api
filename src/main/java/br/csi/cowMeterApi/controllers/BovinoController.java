package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.BovinoDto;
import br.csi.cowMeterApi.dtos.BovinoUpdateDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import br.csi.cowMeterApi.models.Bovino;
import br.csi.cowMeterApi.services.BovinoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bovino")
public class BovinoController {
    private final BovinoService bovinoService;

    public BovinoController(BovinoService bovinoService) {
        this.bovinoService = bovinoService;
    }

    @PostMapping("/saveBovino")
    public ResponseEntity<String> salvarBovino(@Valid @RequestBody BovinoDto bovinoDto) throws Exception {
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
            @Valid @RequestBody BovinoUpdateDto bovinoDto,
            @Valid @PathVariable Long id
    ) throws Exception {
        if (isValidUpdateDto(bovinoDto)) {
            Bovino bovino = bovinoService.atualizarBovino(id, bovinoDto);
            if (bovino != null) {
                return new ResponseEntity<>("Bovino atualizado com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao atualizar dados do bovino!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        throw new InvalidRequestDataException("Os dados enviados para atualizar o bovino são inválidos!");
    }

        @DeleteMapping("/deleteBovino/{id}")
        public ResponseEntity<Boolean> deletarBovino(@Valid @PathVariable Long id) throws Exception {
            if (id != null) {
                boolean res = bovinoService.deletarBovino(id);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            throw new InvalidRequestDataException("Identificador do bovino não encontrado!");
        }

    @GetMapping("/getBovino/{id}")
    public ResponseEntity<Bovino> getBovino(@Valid @PathVariable Long id)  {
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
    public ResponseEntity<List<Bovino>> getAllBovinos() {
        List<Bovino> bovinos = bovinoService.getAllBovinos();
        return new ResponseEntity<>(bovinos, HttpStatus.OK);
    }


    private boolean isValidUpdateDto(BovinoUpdateDto bovinoDto) {
        return bovinoDto.nome() != null
                && !bovinoDto.nome().isBlank()
                && bovinoDto.castrado() != null
                && !bovinoDto.observacoes().isBlank()
                && bovinoDto.observacoes() != null;
    }

    private boolean isValidDto(BovinoDto bovinoDto) {
        return bovinoDto.idRaca() != null &&
                bovinoDto.nome() != null && !bovinoDto.nome().isBlank() &&
                bovinoDto.dataNasc() != null &&
                bovinoDto.sexo() != null &&
                bovinoDto.tipoBovino() != null &&
                bovinoDto.idUsuario() != null;
    }
}
