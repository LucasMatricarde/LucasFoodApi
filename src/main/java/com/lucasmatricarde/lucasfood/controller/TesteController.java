package com.lucasmatricarde.lucasfood.controller;

import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import com.lucasmatricarde.lucasfood.domain.repository.RestauranteRepository;
import com.lucasmatricarde.lucasfood.infrastructure.respository.spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/findByName")
    public List<Cozinha> findByName(String name){
        return cozinhaRepository.findByNomeContaining(name);
    }

    @GetMapping("/cozinhas/uniqueByName")
    public Optional<Cozinha> findOptionalByName(String name){
        return cozinhaRepository.findOptionalByNome(name);
    }

    @GetMapping("/cozinhas/existsByName")
    public boolean cozinhaExists(String name){
        return cozinhaRepository.existsByNome(name);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> consultarPorNome(String nome, Long idCozinha){
        return restauranteRepository.consultarPorNome(nome, idCozinha);
    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome){
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restauranteTop2PorNome(String nome){
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/count-por-cozinha")
    public int restauranteCountPorCozinha(Long idCozinha){
        return restauranteRepository.countByCozinhaId(idCozinha);
    }

    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restauranteComFreteGratis(String nome){
        return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis()
                                    .and(RestauranteSpecs.comNomeSemelhante(nome)));
    }
}
