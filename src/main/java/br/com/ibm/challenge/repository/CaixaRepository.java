package br.com.ibm.challenge.repository;

import br.com.ibm.challenge.domain.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CaixaRepository extends JpaRepository<Caixa, UUID> {
}
