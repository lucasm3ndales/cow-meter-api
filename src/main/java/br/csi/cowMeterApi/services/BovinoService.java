package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.dtos.BovinoDto;
import br.csi.cowMeterApi.models.Bovino;
import br.csi.cowMeterApi.models.Raca;
import br.csi.cowMeterApi.repositories.BovinoRepository;
import br.csi.cowMeterApi.repositories.RacaRepository;
import br.csi.cowMeterApi.utils.enumUtils.EnumUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BovinoService {
    private final BovinoRepository bovinoRepository;
    private final RacaRepository racaRepository;

    public BovinoService(BovinoRepository bovinoRepository, RacaRepository racaRepository) {
        this.bovinoRepository = bovinoRepository;
        this.racaRepository = racaRepository;
    }

    public Bovino salvarBovino(BovinoDto bovinoDto) {
        Raca raca = racaRepository.findById(bovinoDto.idRaca())
                .orElseThrow(() -> new EntityNotFoundException("Raça não encontrada com o ID: " + bovinoDto.idRaca()));


        Bovino.Sexo sexo = null;
        Bovino.TipoBovino tipoBovino = null;
        if(!EnumUtils.isRoleValid(bovinoDto.sexo())) {
        } else {
            sexo = Bovino.Sexo.valueOf(bovinoDto.sexo());
        }

        if(!EnumUtils.isRoleValid(bovinoDto.tipoBovino())) {

        } else {
            tipoBovino = Bovino.TipoBovino.valueOf(bovinoDto.tipoBovino());
        }

        Date currentDate = new Date(System.currentTimeMillis());
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        Bovino bovino = new Bovino();
        bovino.setRaca(raca);
        bovino.setNome(bovinoDto.nome());
        bovino.setDataNasc(bovinoDto.dataNasc());
        bovino.setSexo(sexo);
        bovino.setObservacoes(bovinoDto.observacoes());
        bovino.setCastrado(bovinoDto.castrado());
        bovino.setTipoBovino(tipoBovino);
        bovino.setCriadoEm(currentTimestamp);
        bovino.setAtualizadoEm(currentTimestamp);

        return bovinoRepository.save(bovino);
    }

    public Bovino atualizarBovino(Long id, BovinoDto bovinoDto) {
        Raca raca = racaRepository.findById(bovinoDto.idRaca())
                .orElseThrow(() -> new EntityNotFoundException("Raça não encontrada com o ID: " + bovinoDto.idRaca()));

        Bovino bovino = bovinoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bovino não encontrado com o ID: " + id));

        Bovino.Sexo sexo = null;
        Bovino.TipoBovino tipoBovino = null;
        if(!EnumUtils.isRoleValid(bovinoDto.sexo())) {
        } else {
            sexo = Bovino.Sexo.valueOf(bovinoDto.sexo());
        }

        if(!EnumUtils.isRoleValid(bovinoDto.tipoBovino())) {

        } else {
            tipoBovino = Bovino.TipoBovino.valueOf(bovinoDto.tipoBovino());
        }

        Date currentDate = new Date(System.currentTimeMillis());
        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());

        bovino.setRaca(raca);
        bovino.setNome(bovinoDto.nome());
        bovino.setDataNasc(bovinoDto.dataNasc());
        bovino.setSexo(sexo);
        bovino.setObservacoes(bovinoDto.observacoes());
        bovino.setCastrado(bovinoDto.castrado());
        bovino.setTipoBovino(tipoBovino);
        bovino.setAtualizadoEm(currentTimestamp);


        return bovinoRepository.save(bovino);
    }

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
