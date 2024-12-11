package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {

    List<Cidade> findAll ();
    Cidade findById (Long id);
    void delete (Long id);
    Cidade save (Cidade cidade);
}
