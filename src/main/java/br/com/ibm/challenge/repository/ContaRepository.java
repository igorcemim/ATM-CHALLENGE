package br.com.ibm.challenge.repository;

import br.com.ibm.challenge.domain.cliente.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumeroAndAgencia(Integer numero, Integer agencia);
}
