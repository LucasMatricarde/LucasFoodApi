package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> findAll();
    Cozinha findById(Long id);
    void delete(Long id);
    Cozinha save(Cozinha cozinha);
}
