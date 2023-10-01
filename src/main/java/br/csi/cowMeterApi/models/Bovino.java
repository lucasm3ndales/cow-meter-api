package br.csi.cowMeterApi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Raca raca;


    private String nome;
    private BigDecimal peso;

    @Temporal(TemporalType.DATE)
    private Date dataNasc;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private RoleSexo sexo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada_cio")
    private Date dataEntradaCio;

    private String observacoes;
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

}
