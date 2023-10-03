package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.dtos.UsuarioDto;
import br.csi.cowMeterApi.exceptions.InvalidCpfException;
import br.csi.cowMeterApi.exceptions.InvalidEnumException;
import br.csi.cowMeterApi.models.Role;
import br.csi.cowMeterApi.models.Usuario;
import br.csi.cowMeterApi.repositories.UsuarioRepository;
import br.csi.cowMeterApi.utils.enumUtils.EnumUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public boolean salvarUsuario(UsuarioDto usuarioDto) throws Exception {
        try {
            Optional<Usuario> isUsuario = usuarioRepository.findByCpf(usuarioDto.cpf());

            if(isUsuario.isPresent()) {
                throw new InvalidCpfException("CPF já existente no sistema!");
            }

//            if(!Usuario.isValidCpf(usuarioDto.cpf())) {
//                throw new InvalidCpfException("CPF inválido: " + usuarioDto.cpf());
//            }

            Role role = null;
            if(!EnumUtils.isRoleValid(usuarioDto.role())) {
                throw new InvalidEnumException("Permissão de usuário Inválida");
            } else {
                role = Role.valueOf(usuarioDto.role());
            }

            Usuario usuario = new Usuario();
            usuario.setNome(usuarioDto.nome().toLowerCase());
            usuario.setCpf(usuarioDto.cpf().replaceAll("\\D", ""));
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioDto.senha()));
            usuario.setActive(true);
            usuario.setRole(role);
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public boolean atualizarUsuario(UsuarioDto usuarioDto, Long id) throws Exception {
        try {
            Optional<Usuario> isUsuario = usuarioRepository.findByCpf(usuarioDto.cpf());

            if(isUsuario.isPresent()) {
                throw new InvalidCpfException("CPF já existente no sistema!");
            }

//            if(!Usuario.isValidCpf(usuarioDto.cpf())) {
//                throw new InvalidCpfException("CPF inválido: " + usuarioDto.cpf());
//            }

            Role role = null;
            if(!EnumUtils.isRoleValid(usuarioDto.role())) {
                throw new InvalidEnumException("Permissão de usuário Inválida");
            } else {
                role = Role.valueOf(usuarioDto.role());
            }

            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            if(!bcrypt.matches(usuarioDto.senha(), usuario.getSenha())) {
                usuario.setSenha(bcrypt.encode(usuarioDto.senha()));
            }

            usuario.setNome(usuarioDto.nome().toLowerCase());
//            usuario.setCpf(usuarioDto.cpf().replaceAll("\\D", ""));
            usuario.setActive(usuarioDto.active());
            usuario.setRole(role);
            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Transactional
    public boolean desativarUsuario(Long id) throws Exception {
        try {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

            usuario.setActive(!usuario.getActive());

            usuarioRepository.save(usuario);
            return usuario.getActive();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
    }

    public Page<Usuario> getAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

}