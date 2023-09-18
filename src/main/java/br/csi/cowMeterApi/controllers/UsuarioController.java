package br.csi.cowMeterApi.controllers;

import br.csi.cowMeterApi.dtos.UsuarioDto;
import br.csi.cowMeterApi.exceptions.InvalidRequestDataException;
import br.csi.cowMeterApi.models.Usuario;
import br.csi.cowMeterApi.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cowMeterApi/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/saveUsuario")
    public ResponseEntity<String> salvarUsuario(@RequestBody UsuarioDto usuarioDto) throws Exception {
        if(isValidDto(usuarioDto)) {
            boolean res =  usuarioService.salvarUsuario(usuarioDto);
            if(res) {
                return new ResponseEntity<>("Usuário salvo com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao salvar dados de usuário!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        throw new InvalidRequestDataException("Os dados enviados para salvar o usuário são inválidos!");
    }

    @PutMapping("/updateUsuario/{id}")
    public ResponseEntity<String> atualizarUsuario(
            @RequestBody UsuarioDto usuarioDto,
            @PathVariable Long id
    ) throws Exception {
        if(isValidDto(usuarioDto) && id != null) {
            boolean res =  usuarioService.atualizarUsuario(usuarioDto, id);
            if(res) {
                return new ResponseEntity<>("Usuário atualizado com sucesso!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Erro ao atualizar dados de usuário!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        throw new InvalidRequestDataException("Os dados enviados para atuliazar o usuário são inválidos!");
    }

    @PutMapping("/activeUsuario/{id}")
    public ResponseEntity<Boolean> desativarUsuario(
            @PathVariable Long id
    ) throws Exception {
        if(id != null) {
            boolean res =  usuarioService.desativarUsuario(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        throw new InvalidRequestDataException("Identificador do usuário não encontrado!");
    }

    @GetMapping("/getUsuario/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) throws Exception  {
        if(id != null) {
            Usuario res =  usuarioService.getUsuario(id);
            if(res != null) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
        throw new InvalidRequestDataException("Identificador de usuário não encontrado!");
    }

    @GetMapping("/getAllUsuarios")
    public ResponseEntity<Page<Usuario>> getAllUsuario(@RequestParam Pageable pageable) {
        if(pageable != null) {
            Page<Usuario> usuarios = usuarioService.getAllUsuarios(pageable);
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        Page<Usuario> usuarios = usuarioService.getAllUsuarios(PageRequest.of(0, 10, Sort.unsorted()));
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    private boolean isValidDto(UsuarioDto usuarioDto) {
        return usuarioDto.nome() != null && !usuarioDto.nome().isBlank()
                && usuarioDto.senha() != null && !usuarioDto.senha().isBlank()
                && usuarioDto.cpf() != null && !usuarioDto.cpf().isBlank()
                && usuarioDto.role() != null && !usuarioDto.role().isBlank()
                && usuarioDto.active() != null;
    }
}