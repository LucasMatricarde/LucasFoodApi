package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> findAll ();
    Restaurante findById (Long id);
    void delete (Restaurante cozinha);
    Restaurante save (Restaurante cozinha);

}
