package com.lucasmatricarde.lucasfood.domain.service;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import com.lucasmatricarde.lucasfood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante save(Restaurante restaurante){
        Long idCozinha = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(idCozinha)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("A cozinha com o id %d não foi encontrada", idCozinha)));

        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }
}
