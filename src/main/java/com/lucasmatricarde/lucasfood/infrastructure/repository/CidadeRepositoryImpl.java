package com.lucasmatricarde.lucasfood.infrastructure.repository;

import com.lucasmatricarde.lucasfood.domain.model.Cidade;
import com.lucasmatricarde.lucasfood.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> findAll () {
        return entityManager.createQuery("SELECT c FROM Cidade c", Cidade.class).getResultList();
    }

    @Override
    public Cidade findById (Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public void delete (Long id) {
        Cidade cidade = findById(id);

        if(cidade == null){
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(cidade);
    }

    @Transactional
    @Override
    public Cidade save (Cidade cidade) {
        return entityManager.merge(cidade);
    }
}
