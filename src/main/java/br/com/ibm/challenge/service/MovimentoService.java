package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.movimento.Movimento;
import br.com.ibm.challenge.repository.MovimentoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovimentoService extends CrudService<Movimento, UUID> {

    private final MovimentoRepository repository;

    public MovimentoService(MovimentoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Movimento, UUID> getRepository() {
        return repository;
    }
}
