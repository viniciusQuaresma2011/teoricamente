package com.example.contratolocacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.contratolocacao.model.ContratoLocacao;



@Repository
public interface ContratoLocacaoRepository extends JpaRepository<ContratoLocacao, Long> {

}
