package com.lucasmatricarde.lucasfood.jpa;

import com.lucasmatricarde.lucasfood.LucasFoodApiApplication;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {
    public static void main (String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(LucasFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);
        Cozinha cozinha = cozinhaRepository.findById(1L);

        System.out.println(cozinha.getNome());
    }
}
