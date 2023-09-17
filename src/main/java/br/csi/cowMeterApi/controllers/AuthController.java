package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.AuthDto;
import br.csi.cowMeterApi.dtos.LoginDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import br.csi.cowMeterApi.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cowMeterApi/auth")
public class AuthController {
    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginDto> authentication(@RequestBody AuthDto authDto) {
        if(!authDto.cpf().isBlank() && !authDto.senha().isBlank()) {
            LoginDto loginDto = loginService.authenticateUsuario(authDto);
            return new ResponseEntity<>(loginDto, HttpStatus.OK);
        }
        throw new InvalidRequestDataException("Os dados enviados para autenticação são inválidos!");
    }
}
