package br.csi.cowMeterApi.dtos;

import br.csi.cowMeterApi.models.Sexo;
import br.csi.cowMeterApi.models.TipoBovino;
import java.math.BigDecimal;
import java.util.Date;

public record BovinoDto(
        Long idRaca,
        String nome,
        BigDecimal peso,
        Date dataNasc,
        Sexo sexo,
        Date dataEntradaCio,
        String observacoes,
        boolean castrado,
        TipoBovino tipoBovino
){}
