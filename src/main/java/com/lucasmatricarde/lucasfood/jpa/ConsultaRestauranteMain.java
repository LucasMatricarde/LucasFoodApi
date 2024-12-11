package com.lucasmatricarde.lucasfood.jpa;

import com.lucasmatricarde.lucasfood.LucasFoodApiApplication;
import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import com.lucasmatricarde.lucasfood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {
    public static void main (String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(LucasFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        for (Restaurante restaurante : restaurantes) {
            System.out.printf("%s - %f - %s\n", restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
        }
    }
}
