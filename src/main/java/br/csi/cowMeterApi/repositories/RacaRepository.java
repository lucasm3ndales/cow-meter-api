package br.csi.cowMeterApi.repositories;

import br.csi.cowMeterApi.models.Bovino;
import br.csi.cowMeterApi.models.Raca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {


}