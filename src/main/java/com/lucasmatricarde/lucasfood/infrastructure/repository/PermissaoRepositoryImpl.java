package com.lucasmatricarde.lucasfood.infrastructure.repository;

import com.lucasmatricarde.lucasfood.domain.model.Permissao;
import com.lucasmatricarde.lucasfood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> findAll () {
        return entityManager.createQuery("SELECT c FROM Permissao c", Permissao.class).getResultList();
    }

    @Override
    public Permissao findById (Long id) {
        return entityManager.find(Permissao.class, id);
    }

    @Override
    public void delete (Permissao permissao) {
        permissao = findById(permissao.getId());
        entityManager.remove(permissao);
    }

    @Override
    public Permissao save (Permissao permissao) {
        return entityManager.merge(permissao);
    }
}
