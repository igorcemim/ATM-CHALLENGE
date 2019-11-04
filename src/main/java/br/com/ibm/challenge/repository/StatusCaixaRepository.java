package br.com.ibm.challenge.repository;

import br.com.ibm.challenge.domain.StatusCaixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatusCaixaRepository extends JpaRepository<StatusCaixa, UUID> {
}
