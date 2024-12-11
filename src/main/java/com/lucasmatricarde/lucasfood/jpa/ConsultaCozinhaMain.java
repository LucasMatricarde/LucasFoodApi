package com.lucasmatricarde.lucasfood.jpa;

import com.lucasmatricarde.lucasfood.LucasFoodApiApplication;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {
    public static void main (String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(LucasFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);
        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha.getNome());
        }
    }
}
