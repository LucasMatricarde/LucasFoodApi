package com.lucasmatricarde.lucasfood.infrastructure.repository;

import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import com.lucasmatricarde.lucasfood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> findAll () {
        return entityManager.createQuery("SELECT c FROM Restaurante c", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante findById (Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public void delete (Restaurante cozinha) {
        cozinha = findById(cozinha.getId());

        if(cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(cozinha);
    }

    @Transactional
    @Override
    public Restaurante save (Restaurante cozinha) {
        return entityManager.merge(cozinha);
    }
}
