package br.csi.cowMeterApi.services;

import br.csi.cowMeterApi.dtos.SaudeDto;
import br.csi.cowMeterApi.models.Bovino;
import br.csi.cowMeterApi.models.Saude;
import br.csi.cowMeterApi.repositories.BovinoRepository;
import br.csi.cowMeterApi.repositories.SaudeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SaudeService {
    private final SaudeRepository saudeRepository;
    private final BovinoRepository bovinoRepository;
    public SaudeService(SaudeRepository saudeRepository, BovinoRepository bovinoRepository) {
        this.saudeRepository = saudeRepository;
        this.bovinoRepository = bovinoRepository;
    }

    @Transactional
    public Saude saveSaude(SaudeDto saudeDto) throws Exception {
        try {
            Bovino bovino = bovinoRepository.findById(saudeDto.idBovino())
                    .orElseThrow(() -> new EntityNotFoundException("O ID do bovino requisitado não existe!"));

            Date currentDate = new java.sql.Date(System.currentTimeMillis());

            SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(saudeDto.dataEntradaCio());
            Date dataTratamento  = sdf.parse(saudeDto.dataTratamento());

            Saude saude = new Saude();
            saude.setObservacoes(saudeDto.observacoes());
            saude.setMedicamentos(saudeDto.medicamentos());
            saude.setTipoTratamento(saudeDto.tipoTratamento());
            saude.setPeso(saudeDto.peso());
            saude.setDataEntradaCio(date);
            saude.setDataTratamento(dataTratamento);
            saude.setBovino(bovino);
            saude.setCriadoEm(currentDate);
            saude.setAtualizadoEm(currentDate);
            saudeRepository.save(saude);
            return saude;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    @Transactional
    public Saude updateSaude(SaudeDto saudeDto, Long id) throws Exception {
        try {
            Bovino bovino = bovinoRepository.findById(saudeDto.idBovino())
                    .orElseThrow(() -> new EntityNotFoundException("O ID do bovino requisitado não existe!"));

            Saude saude = saudeRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("O ID da feira requisitada não existe!"));

            java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());

            SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(saudeDto.dataEntradaCio());
            Date dataTratamento  = sdf.parse(saudeDto.dataTratamento());

            saude.setObservacoes(saudeDto.observacoes());
            saude.setMedicamentos(saudeDto.medicamentos());
            saude.setTipoTratamento(saudeDto.tipoTratamento());
            saude.setPeso(saudeDto.peso());
            saude.setDataEntradaCio(date);
            saude.setDataTratamento(dataTratamento);
            saude.setBovino(bovino);
            saude.setAtualizadoEm(currentDate);
            saudeRepository.save(saude);
            return saude;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }


}
