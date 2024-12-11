package com.lucasmatricarde.lucasfood.domain.service;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Estado;
import com.lucasmatricarde.lucasfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado save(Estado estado){
        return estadoRepository.save(estado);
    }

    public void delete(Long id){
        try{
            estadoRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("O estado com o id %d está sendo utilizada", id));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("O estado com o id %d não foi encontrada", id));
        }
    }
}
