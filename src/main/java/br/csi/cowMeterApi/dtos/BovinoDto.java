package br.csi.cowMeterApi.dtos;

import br.csi.cowMeterApi.models.Bovino;
import java.math.BigDecimal;
import java.util.Date;

public record BovinoDto(
        Long idRaca,
        String nome,
        Date dataNasc,
        String sexo,
        String observacoes,
        Boolean castrado,
        String tipoBovino
){}
