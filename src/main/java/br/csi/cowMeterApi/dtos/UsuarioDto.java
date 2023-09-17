package br.csi.cowMeterApi.dtos;

public record UsuarioDto(
        String nome,
        String cpf,
        String senha,
        String role,
        Boolean active
) {}
