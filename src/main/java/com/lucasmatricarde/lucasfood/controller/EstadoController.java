package com.lucasmatricarde.lucasfood.controller;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Estado;
import com.lucasmatricarde.lucasfood.domain.repository.EstadoRepository;
import com.lucasmatricarde.lucasfood.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService estadoService;
    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @GetMapping("/findAll")
    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        Estado estado = estadoRepository.findById(id);

        if (estado == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estado);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Estado estado){
        try {
            estado = estadoService.save(estado);

            return ResponseEntity.status(HttpStatus.CREATED).body(estado);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Estado estado){
        try {
            Estado estadoAtual = estadoRepository.findById(id);

            if(estadoAtual == null){
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = cadastroEstadoService.save(estadoAtual);

            return ResponseEntity.ok(estadoAtual);

        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            cadastroEstadoService.delete(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
