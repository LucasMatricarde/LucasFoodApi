package com.lucasmatricarde.lucasfood.infrastructure.repository;

import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> findAll () {
        return entityManager.createQuery("SELECT c FROM Cozinha c", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha findById (Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public void delete (Long id) {
      Cozinha cozinha = findById(id);

      if(cozinha == null){
          throw new EmptyResultDataAccessException(1);
      }

      entityManager.remove(cozinha);
    }

    @Transactional
    @Override
    public Cozinha save (Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }
}
