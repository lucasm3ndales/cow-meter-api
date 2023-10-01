package br.csi.cowMeterApi.dtos;

import br.csi.cowMeterApi.models.Role;
import br.csi.cowMeterApi.models.RoleSexo;
import br.csi.cowMeterApi.models.TipoBovino;
import java.math.BigDecimal;
import java.util.Date;

public record BovinoDto(
        Long idRaca,
        String nome,
        BigDecimal peso,
        Date dataNasc,
        RoleSexo sexo,
        Date dataEntradaCio,
        String observacoes,
        boolean castrado,
        TipoBovino tipoBovino
){}
