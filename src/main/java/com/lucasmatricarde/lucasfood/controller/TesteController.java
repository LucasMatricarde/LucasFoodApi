package com.lucasmatricarde.lucasfood.controller;

import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

//    @GetMapping("/cozinhas/findByName")
//    public List<Cozinha> findByName(@RequestParam("name") String name){
//        return cozinhaRepository.findByName(name);
//    }
}
