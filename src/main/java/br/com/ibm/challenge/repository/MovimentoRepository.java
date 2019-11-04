package br.com.ibm.challenge.repository;

import br.com.ibm.challenge.domain.movimento.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, UUID> {
}
