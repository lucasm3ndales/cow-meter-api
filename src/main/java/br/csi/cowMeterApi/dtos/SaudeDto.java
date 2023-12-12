package br.csi.cowMeterApi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public record SaudeDto(
        @NotBlank
        @NotNull
        String tipoTratamento,
        @NotBlank
        @NotNull
        String dataTratamento,
        @NotBlank
        @NotNull
        String medicamentos,
        @NotBlank
        @NotNull
        String observacoes,
        @NotBlank
        @NotNull
        String dataEntradaCio,
        @NotNull
        @Positive
        BigDecimal peso,
        @NotNull
        Long idBovino
) {}
