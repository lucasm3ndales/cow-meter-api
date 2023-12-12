package br.csi.cowMeterApi.dtos;

import br.csi.cowMeterApi.models.Bovino;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record BovinoDto(
        @NotNull
        Long idRaca,
        @NotBlank
        @NotNull
        String nome,
        @NotNull
        String dataNasc,
        @NotNull
        String sexo,
        @NotBlank
        @NotNull
        String observacoes,
        @NotNull
        Boolean castrado,
        @NotNull
        String tipoBovino,
        @NotNull
        Long idUsuario
){}
