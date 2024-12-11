package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
