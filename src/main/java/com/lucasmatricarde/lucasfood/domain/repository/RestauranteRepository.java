package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaFreteAfter, BigDecimal taxaFreteBefore);

    @Query("from Restaurante where nome like %:nome% and cozinha.id = :idCozinha")
    List<Restaurante> consultarPorNome(String nome, Long idCozinha);

    //List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long idCozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long idCozinha);
}
