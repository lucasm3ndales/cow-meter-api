package br.csi.cowMeterApi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
    @Column(name = "IdRegistro")
    private Long idRegistro;

    @ManyToOne
    private Bovino bovino;

    @Column(name = "tipoTratamento", nullable = false, length = 100)
    private String tipoTratamento;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataTratamento", nullable = false)
    private Date dataTratamento;

    @Column(name = "medicamentos", columnDefinition = "TEXT")
    private String medicamentos;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
}
