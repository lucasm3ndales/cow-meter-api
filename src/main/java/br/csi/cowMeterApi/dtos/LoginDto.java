package br.csi.cowMeterApi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String nome;
    private Long id;
    private String role;
    private Boolean active;
}
