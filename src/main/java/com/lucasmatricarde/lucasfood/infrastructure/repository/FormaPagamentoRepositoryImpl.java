package com.lucasmatricarde.lucasfood.infrastructure.repository;

import com.lucasmatricarde.lucasfood.domain.model.FormaPagamento;
import com.lucasmatricarde.lucasfood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaPagamento> findAll () {
        return entityManager.createQuery("SELECT c FROM FormaPagamento c", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento findById (Long id) {
        return entityManager.find(FormaPagamento.class, id);
    }

    @Override
    public void delete (FormaPagamento formaPagamento) {
        formaPagamento = findById(formaPagamento.getId());
        entityManager.remove(formaPagamento);
    }

    @Override
    public FormaPagamento save (FormaPagamento formaPagamento) {
        return entityManager.merge(formaPagamento);
    }
}
