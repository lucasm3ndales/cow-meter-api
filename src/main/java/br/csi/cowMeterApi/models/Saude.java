package br.csi.cowMeterApi.models;

import jakarta.persistence.*;
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
    private Bovino bovino;
    @Column(name = "peso", nullable = false)
    private BigDecimal peso;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entradacio")
    private Date dataEntradaCio;
    @Column(name = "tipo_tratamento", nullable = false, length = 100)
    private String tipoTratamento;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_tratamento", nullable = false)
    private Date dataTratamento;
    @Column(name = "medicamentos", columnDefinition = "TEXT")
    private String medicamentos;
    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "criado_em")
    private Date criadoEm;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "atualizado_em")
    private Date atualizadoEm;
}
