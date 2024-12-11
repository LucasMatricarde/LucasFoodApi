package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> findAll ();
    Estado findById (Long id);
    void delete (Long id);
    Estado save (Estado estado);
}
