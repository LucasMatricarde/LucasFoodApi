package com.lucasmatricarde.lucasfood.domain.service;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Cozinha;
import com.lucasmatricarde.lucasfood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    CozinhaRepository cozinhaRepository;

    public Cozinha save(Cozinha cozinha){
        return cozinhaRepository.save(cozinha);
    }

    public void delete(Long id){
        try{
            cozinhaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("A cozinha com o id %d está sendo utilizada", id));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("A cozinha com o id %d não foi encontrada", id));
        }
    }
}
