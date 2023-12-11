package br.csi.cowMeterApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bovino")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bovino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    private Raca raca;
    @Column(name = "nome", nullable = false)
    @NotBlank
    @NotNull
    private String nome;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc", nullable = false)
    @NotNull
    private Date dataNasc;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    @NotBlank
    @NotNull
    private Sexo sexo;
    @Column(name = "observacoes", nullable = false, columnDefinition = "TEXT")
    private String observacoes;
    @Column(name = "castrado", nullable = false)
    @NotNull
    private boolean castrado;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_bovino")
    @NotBlank
    @NotNull
    private TipoBovino tipoBovino;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criado_em")
    @NotNull
    private Date criadoEm;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "atualizado_em")
    @NotNull
    private Date atualizadoEm;
    @ManyToOne
    @JsonBackReference
    private Usuario usuario;

    public enum TipoBovino {
        VACA,
        BOI,
        TOURO,
        BEZERRO,
        NOVILHA
    }

    public enum Sexo {
        MACHO,
        FEMEA
    }


}
