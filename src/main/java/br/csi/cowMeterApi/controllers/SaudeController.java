package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.SaudeDto;
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

    }

    private boolean isValidDto(SaudeDto saudeDto) {

    }
}
