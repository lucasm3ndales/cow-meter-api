package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.dtos.BovinoDto;
import br.csi.cowMeterApi.exceptions.InvalidEnumException;
import br.csi.cowMeterApi.models.Bovino;
import br.csi.cowMeterApi.models.Raca;
import br.csi.cowMeterApi.models.Usuario;
import br.csi.cowMeterApi.repositories.BovinoRepository;
import br.csi.cowMeterApi.repositories.RacaRepository;
import br.csi.cowMeterApi.repositories.UsuarioRepository;
import br.csi.cowMeterApi.utils.enumUtils.EnumUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BovinoService {
    private final BovinoRepository bovinoRepository;
    private final RacaRepository racaRepository;
    private final UsuarioRepository usuarioRepository;

    public BovinoService(BovinoRepository bovinoRepository, RacaRepository racaRepository, UsuarioRepository usuarioRepository) {
        this.bovinoRepository = bovinoRepository;
        this.racaRepository = racaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Bovino salvarBovino(BovinoDto bovinoDto) throws Exception {
        try{
            Raca raca = racaRepository.findById(bovinoDto.idRaca())
                    .orElseThrow(() -> new EntityNotFoundException("Raça não encontrada com o ID: " + bovinoDto.idRaca()));
            Usuario usuario = usuarioRepository.findById(bovinoDto.idUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + bovinoDto.idUsuario()));


            Bovino.Sexo sexo = EnumUtils.stringToEnum(Bovino.Sexo.class, bovinoDto.sexo());
            Bovino.TipoBovino tipoBovino = EnumUtils.stringToEnum(Bovino.TipoBovino.class, bovinoDto.tipoBovino());
            if(sexo.name().isBlank()) {
                throw new InvalidEnumException("Sexo inválido!");
            }

            if(tipoBovino.name().isBlank()) {
                throw new InvalidEnumException("Tipo bovino inválido!");
            }

            Date currentDate = new Date(System.currentTimeMillis());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(bovinoDto.dataNasc());

            Bovino bovino = new Bovino();
            bovino.setRaca(raca);
            bovino.setNome(bovinoDto.nome());
            bovino.setDataNasc(date);
            bovino.setSexo(sexo);
            bovino.setObservacoes(bovinoDto.observacoes());
            bovino.setCastrado(bovinoDto.castrado());
            bovino.setTipoBovino(tipoBovino);
            bovino.setCriadoEm(currentDate);
            bovino.setAtualizadoEm(currentDate);
            bovino.setUsuario(usuario);

            return bovinoRepository.save(bovino);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    @Transactional
    public Bovino atualizarBovino(Long id, BovinoDto bovinoDto) throws Exception {
        try{
            Raca raca = racaRepository.findById(bovinoDto.idRaca())
                    .orElseThrow(() -> new EntityNotFoundException("Raça não encontrada com o ID: " + bovinoDto.idRaca()));

            Bovino bovino = bovinoRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Bovino não encontrado com o ID: " + id));

            Bovino.Sexo sexo = EnumUtils.stringToEnum(Bovino.Sexo.class, bovinoDto.sexo());
            Bovino.TipoBovino tipoBovino = EnumUtils.stringToEnum(Bovino.TipoBovino.class, bovinoDto.tipoBovino());
            if(sexo.name().isBlank()) {
                throw new InvalidEnumException("Sexo inválido!");
            }

            if(tipoBovino.name().isBlank()) {
                throw new InvalidEnumException("Tipo bovino inválido!");
            }

            Date currentDate = new Date(System.currentTimeMillis());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) sdf.parse(bovinoDto.dataNasc());

            bovino.setNome(bovinoDto.nome());
            bovino.setObservacoes(bovinoDto.observacoes());
            bovino.setCastrado(bovinoDto.castrado());
            bovino.setAtualizadoEm(currentDate);



            return bovinoRepository.save(bovino);

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    @Transactional
    public boolean deletarBovino(Long id) {
        try {
            bovinoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Bovino getBovino(Long id) {
        return bovinoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bovino não encontrado com o ID: " + id));
    }

    public List<Bovino> getAllBovinos() {
        return bovinoRepository.findAll();
    }


}
