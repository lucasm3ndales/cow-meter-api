package br.csi.cowMeterApi.repositories;

import br.csi.cowMeterApi.models.Saude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaudeRepository extends JpaRepository<Saude, Long> {
}
