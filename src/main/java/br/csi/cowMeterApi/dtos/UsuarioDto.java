package br.csi.cowMeterApi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
        @NotBlank
        @NotNull
        String nome,
        @NotBlank
        @NotNull
        String cpf,
        @NotBlank
        @NotNull
        String senha,
        @NotBlank
        @NotNull
        String role,
        @NotNull
        Boolean active
) {}
