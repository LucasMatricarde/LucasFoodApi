package com.lucasmatricarde.lucasfood.domain.repository;


import com.lucasmatricarde.lucasfood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
