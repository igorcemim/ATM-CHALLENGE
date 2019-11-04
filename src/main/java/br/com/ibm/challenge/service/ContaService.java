package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.repository.ContaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContaService extends CrudService<Conta, UUID> {

    private JpaRepository<Conta, UUID> repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Conta, UUID> getRepository() {
        return repository;
    }
}
