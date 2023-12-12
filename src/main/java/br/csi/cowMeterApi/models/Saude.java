package br.csi.cowMeterApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "saude")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Saude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JsonBackReference
    private Bovino bovino;
    @Column(name = "peso", nullable = false)
    @NotNull
    @Positive
    private BigDecimal peso;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entradacio")
    private Date dataEntradaCio;
    @Column(name = "tipo_tratamento", nullable = false, length = 100)
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String tipoTratamento;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_tratamento", nullable = false)
    @NotNull
    private Date dataTratamento;
    @Column(name = "medicamentos", columnDefinition = "TEXT")
    private String medicamentos;
    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "criado_em", nullable = false)
    @NotNull
    private Date criadoEm;
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "atualizado_em", nullable = false)
    @NotNull
    private Date atualizadoEm;
}
