package br.com.ibm.challenge.repository;

import br.com.ibm.challenge.domain.caixa.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, UUID> {
}
