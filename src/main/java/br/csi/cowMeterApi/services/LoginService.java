package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.dtos.AuthDto;
import br.csi.cowMeterApi.dtos.LoginDto;
import br.csi.cowMeterApi.models.Usuario;
import br.csi.cowMeterApi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginService(
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginDto authenticateUsuario(AuthDto authDto) {
        Authentication auth = new UsernamePasswordAuthenticationToken(authDto.cpf(), authDto.senha());
        Authentication a =  authenticationManager.authenticate(auth);

        Usuario user = (Usuario) a.getPrincipal();
        String jwt = jwtService.createJwt(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setNome(user.getNome());
        loginDto.setId(user.getId());
        loginDto.setActive(user.getActive());
        loginDto.setRole(user.getRole().name());

        return loginDto;
    }
}
