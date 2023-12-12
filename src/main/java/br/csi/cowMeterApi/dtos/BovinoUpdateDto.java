package br.csi.cowMeterApi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BovinoUpdateDto(
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        String observacoes,
        @NotNull
        Boolean castrado
) {}
