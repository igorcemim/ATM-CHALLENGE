package br.com.ibm.challenge.service;

import br.com.ibm.challenge.domain.EntityId;
import br.com.ibm.challenge.domain.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.function.Supplier;

abstract public class CrudService<T extends EntityId<ID>, ID> {

    abstract protected JpaRepository<T, ID> getRepository();

    private Supplier<EntityNotFoundException> notFoundexceptionSupplier =
            () -> new EntityNotFoundException("Registro nao encontrado.");

    public T create(T entity) {
        return getRepository().save(entity);
    }

    public T update(T entity) throws EntityNotFoundException {
        return getRepository().findById(entity.getId())
                .map(e -> getRepository().save(e))
                .orElseThrow(notFoundexceptionSupplier);
    }

    public void delete(ID id) throws EntityNotFoundException {
        getRepository().findById(id)
                .map(e -> {
                    getRepository().deleteById(e.getId());
                    return e;
                })
                .orElseThrow(notFoundexceptionSupplier);
    }

    public Page<T> list(Pageable page) {
        return getRepository().findAll(page);
    }

    public T find(ID id) throws EntityNotFoundException {
        return getRepository().findById(id)
                .orElseThrow(notFoundexceptionSupplier);
    }

}
