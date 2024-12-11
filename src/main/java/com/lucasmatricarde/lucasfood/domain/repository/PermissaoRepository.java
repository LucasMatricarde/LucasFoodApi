package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
