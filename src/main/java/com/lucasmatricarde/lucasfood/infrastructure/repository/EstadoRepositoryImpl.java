package com.lucasmatricarde.lucasfood.infrastructure.repository;

import com.lucasmatricarde.lucasfood.domain.model.Estado;
import com.lucasmatricarde.lucasfood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> findAll () {
        return entityManager.createQuery("SELECT c FROM Estado c", Estado.class).getResultList();
    }

    @Override
    public Estado findById (Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public void delete (Long id) {
        Estado estado = findById(id);

        if(estado == null){
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(estado);
    }

    @Transactional
    @Override
    public Estado save (Estado estado) {
        return entityManager.merge(estado);
    }
}
