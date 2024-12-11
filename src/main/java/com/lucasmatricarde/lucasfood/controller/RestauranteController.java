package com.lucasmatricarde.lucasfood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Restaurante;
import com.lucasmatricarde.lucasfood.domain.repository.RestauranteRepository;
import com.lucasmatricarde.lucasfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurante> findAll(){
        return restauranteRepository.findAll();
    }

    @GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurante> findById(@PathVariable("id") Long id){
        Restaurante restaurante = restauranteRepository.findById(id);

        if (restaurante == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(restaurante);
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody Restaurante restaurante){
        try {
            restaurante = cadastroRestauranteService.save(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        }catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurante restaurante){
        try {
            Restaurante restauranteAtual = restauranteRepository.findById(id);

            if (restauranteAtual != null) {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

                restauranteAtual = cadastroRestauranteService.save(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<?> updateParcial(@PathVariable Long id, @RequestBody Map<String, Object> params){
        Restaurante restauranteAtual = restauranteRepository.findById(id);

        if (restauranteAtual == null){
            return ResponseEntity.notFound().build();
        }

        merge(params, restauranteAtual);

        return update(id, restauranteAtual);
    }

    private void merge (Map<String, Object> paramsOrigem, Restaurante restauranteDestino) {
        ObjectMapper mapper = new ObjectMapper();
        Restaurante restauranteOrigem = mapper.convertValue(paramsOrigem, Restaurante.class);

        System.out.println(restauranteOrigem);

        paramsOrigem.forEach((nomePropriedade, valorPropriedade)->{
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
