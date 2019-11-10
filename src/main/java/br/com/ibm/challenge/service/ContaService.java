package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.cliente.Conta;
import br.com.ibm.challenge.domain.exception.EntityNotFoundException;
import br.com.ibm.challenge.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContaService extends CrudService<Conta, UUID> {

    private ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ContaRepository getRepository() {
        return repository;
    }

    public Conta findByNumeroAndAgencia(Integer numero, Integer agencia) throws EntityNotFoundException {
        return repository.findByNumeroAndAgencia(numero, agencia)
                .orElseThrow(() -> new EntityNotFoundException("Conta nao encontrada"));
    }

}
