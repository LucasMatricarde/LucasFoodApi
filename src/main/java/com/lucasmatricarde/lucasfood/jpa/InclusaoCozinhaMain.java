package com.lucasmatricarde.lucasfood.jpa;

import com.lucasmatricarde.lucasfood.LucasFoodApiApplication;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {
    public static void main (String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(LucasFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Japonesa Teste");

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setNome("Tailandesa Teste");

        cozinhaRepository.save(cozinha);
        cozinhaRepository.save(cozinha1);
    }
}
