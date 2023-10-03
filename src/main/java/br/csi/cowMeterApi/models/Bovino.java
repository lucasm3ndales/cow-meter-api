package br.csi.cowMeterApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String nome;
    @Column(name = "peso", nullable = false)
    private BigDecimal peso;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc", nullable = false)
    private Date dataNasc;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada_cio")
    private Date dataEntradaCio;
    @Column(name = "observacoes", nullable = false, columnDefinition = "TEXT")
    private String observacoes;
    @Column(name = "castrado", nullable = false)
    private boolean castrado;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_bovino")
    private TipoBovino tipoBovino;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    @ManyToOne
    @JsonBackReference
    private Usuario usuario;

}
