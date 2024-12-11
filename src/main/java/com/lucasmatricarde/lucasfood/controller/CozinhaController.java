package com.lucasmatricarde.lucasfood.controller;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import com.lucasmatricarde.lucasfood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Optional<Cozinha>> findById(@PathVariable("id") Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cozinha);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha save (@RequestBody Cozinha cozinha){
        return cadastroCozinhaService.save(cozinha);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Cozinha>> update(@PathVariable Long id, @RequestBody Cozinha cozinha){
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

        if (cozinhaAtual.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
        cadastroCozinhaService.save(cozinhaAtual.get());

        return ResponseEntity.ok(cozinhaAtual);
    }

    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable Long id){
        try {
            if (!cozinhaRepository.existsById(id)) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe um cadastro de cozinha com código %d", id));
            }
            cozinhaRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
