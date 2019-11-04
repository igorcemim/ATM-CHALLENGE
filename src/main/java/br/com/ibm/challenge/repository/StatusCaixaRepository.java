package br.com.ibm.challenge.repository;

import br.com.ibm.challenge.domain.caixa.StatusCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusCaixaRepository extends JpaRepository<StatusCaixa, UUID> {
}
