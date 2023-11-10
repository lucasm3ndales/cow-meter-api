package br.csi.cowMeterApi.dtos;

import java.math.BigDecimal;
import java.util.Date;

public record SaudeDto(
        String tipoTratamento,
        String dataTratamento,
        String medicamentos,
        String observacoes,
        String criadoEm,
        String atualizadoEm,
        Date dataEntradaCio,
        BigDecimal peso,
        Long idBovino
) {}
