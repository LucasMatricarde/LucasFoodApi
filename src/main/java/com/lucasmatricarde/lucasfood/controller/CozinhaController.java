package com.lucasmatricarde.lucasfood.controller;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import com.lucasmatricarde.lucasfood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cozinha> findById(@PathVariable("id") Long id) {
        Cozinha cozinha = cozinhaRepository.findById(id);

        if (cozinha == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha);

//        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .body(cozinha);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save (@RequestBody Cozinha cozinha){
        return cadastroCozinhaService.save(cozinha);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cozinha> update(@PathVariable Long id, @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.findById(id);

        if (cozinhaAtual == null){
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cadastroCozinhaService.save(cozinhaAtual);

        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cozinha> remove(@PathVariable Long id){
        try {
            cadastroCozinhaService.delete(id);

            return ResponseEntity.noContent().build();
        }catch (EntidadeEmUsoException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }
}
