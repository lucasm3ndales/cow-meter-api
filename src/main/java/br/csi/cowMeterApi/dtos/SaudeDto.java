package br.csi.cowMeterApi.dtos;

public record SaudeDto(
        String tipoTratamento,
        String dataTratamento,
        String medicamentos,
        String observacoes,
        String criadoEm,
        String atualizadoEm,
        Long idBovino
) {}
