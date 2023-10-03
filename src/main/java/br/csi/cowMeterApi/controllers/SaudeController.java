package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.SaudeDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import br.csi.cowMeterApi.services.SaudeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CowMeterApi/saude")
public class SaudeController {
    private final SaudeService saudeService;
    public SaudeController(SaudeService saudeService) {
        this.saudeService = saudeService;
    }

    @PostMapping("/saveSaude")
    public ResponseEntity<String> saveSaude(@RequestBody SaudeDto saudeDto) {
        if(isValidDto(saudeDto)) {

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
