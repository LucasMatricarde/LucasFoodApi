package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Permissao;

import java.util.List;

public interface PermissaoRepository {

    List<Permissao> findAll ();
    Permissao findById (Long id);
    void delete (Permissao permissao);
    Permissao save (Permissao permissao);
}
