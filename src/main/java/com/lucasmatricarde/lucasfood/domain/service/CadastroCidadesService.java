package com.lucasmatricarde.lucasfood.domain.service;

import com.lucasmatricarde.lucasfood.domain.exception.EntidadeEmUsoException;
import com.lucasmatricarde.lucasfood.domain.exception.EntidadeNaoEncontradaException;
import com.lucasmatricarde.lucasfood.domain.model.Cidade;
import com.lucasmatricarde.lucasfood.domain.model.Estado;
import com.lucasmatricarde.lucasfood.domain.repository.CidadeRepository;
import com.lucasmatricarde.lucasfood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroCidadesService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade save(Cidade cidade){
        Long idEstado = cidade.getEstado().getId();
        Optional<Estado> estado = estadoRepository.findById(idEstado);

        if (estado.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("O estado com o id %d não foi encontrado", idEstado));
        }

        cidade.setEstado(estado.get());
        return cidadeRepository.save(cidade);
    }

    public void delete(Long id){
        try{
            cidadeRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(String.format("A cidade com o id %d está sendo utilizada", id));
        }catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("A cidade com o id %d não foi encontrada", id));
        }
    }
}
