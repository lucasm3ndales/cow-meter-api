package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.SaudeDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import br.csi.cowMeterApi.models.Saude;
import br.csi.cowMeterApi.services.SaudeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saude")
public class SaudeController {
    private final SaudeService saudeService;
    public SaudeController(SaudeService saudeService) {
        this.saudeService = saudeService;
    }

    @PostMapping("/saveSaude")
    public ResponseEntity<Saude> saveSaude(@RequestBody SaudeDto saudeDto) throws Exception {
        if(isValidDto(saudeDto)) {
            Saude savedSaude = saudeService.saveSaude(saudeDto);
            if(savedSaude != null) {
                return new ResponseEntity<>(savedSaude, HttpStatus.OK);
            }
        }
        throw new InvalidRequestDataException("Os dados enviados são inválidos");
    }

    @PutMapping("/updateSaude/{id}")
    public ResponseEntity<Saude> saveSaude(@RequestBody SaudeDto saudeDto, @PathVariable Long id) throws Exception {
        if(isValidDto(saudeDto) && id != null) {
            Saude updatedSaude = saudeService.updateSaude(saudeDto, id);
            if(updatedSaude != null) {
                return new ResponseEntity<>(updatedSaude, HttpStatus.OK);
            }
        }
        throw new InvalidRequestDataException("Os dados enviados são inválidos");
    }

    private boolean isValidDto(SaudeDto saudeDto) {
        return !saudeDto.criadoEm().equals("") &&
                !saudeDto.observacoes().equals("") &&
                !saudeDto.medicamentos().equals("") &&
                !saudeDto.tipoTratamento().equals("") &&
                !saudeDto.atualizadoEm().equals("") &&
                !saudeDto.dataTratamento().equals("");
    }
}
