package com.lucasmatricarde.lucasfood.domain.repository;

import com.lucasmatricarde.lucasfood.domain.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {

    List<FormaPagamento> findAll ();
    FormaPagamento findById (Long id);
    void delete (FormaPagamento formaPagamento);
    FormaPagamento save (FormaPagamento formaPagamento);
}
