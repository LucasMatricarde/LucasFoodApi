package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
    //List<Cozinha> findByName(String name);
}
