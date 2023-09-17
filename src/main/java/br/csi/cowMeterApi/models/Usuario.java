package br.csi.cowMeterApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
    @Column(name = "active", nullable = false)
    private Boolean active;
    //TODO: fazer o relacionamento com os bovinos, utilizando Set ao invés de List;

    public static boolean isValidCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if(cpf.length() != 11) {
            return false;
        }

        boolean allEqual = true;
        for(int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                allEqual = false;
                break;
            }
        }
        if(allEqual) {
            return false;
        }

        int[] digits = new int[11];
        for(int i = 0; i < 11; i++) {
            digits[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int sum1 = 0;
        int sum2 = 0;
        for(int i = 0; i < 9; i++) {
            sum1 += digits[i] * (10 - i);
            sum2 += digits[i] * (11 - i);
        }

        int remainder1 = sum1 % 11;
        int remainder2 = sum2 % 11;
        if(remainder1 < 2) {
            if (digits[9] != 0) {
                return false;
            }
        }else {
            if(digits[9] != 11 - remainder1) {
                return false;
            }
        }

        if(remainder2 < 2) {
            if(digits[10] != 0) {
                return false;
            }
        }else {
            if(digits[10] != 11 - remainder2) {
                return false;
            }
        }
        return true;
    }
}
