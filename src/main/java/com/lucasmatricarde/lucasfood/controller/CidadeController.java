package com.lucasmatricarde.lucasfood.controller;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Cidade;
import com.lucasmatricarde.lucasfood.domain.repository.CidadeRepository;
import com.lucasmatricarde.lucasfood.domain.service.CadastroCidadesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadesService cadastroCidadesService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @RequestMapping("/findAll")
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @RequestMapping("/findById/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable("id") Long id){
        Cidade cidade = cidadeRepository.findById(id);

        if (cidade == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cidade);
    }

    @RequestMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Cidade cidade){
        try{
            cidade = cadastroCidadesService.save(cidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Cidade cidade){
        try {
            Cidade cidadeAtual = cidadeRepository.findById(id);

            if(cidadeAtual == null){
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            cidadeAtual = cadastroCidadesService.save(cidadeAtual);
            return ResponseEntity.ok(cidadeAtual);

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id){
        try {
            cadastroCidadesService.delete(id);

            return ResponseEntity.noContent().build();
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
