package com.example.locacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.locacao.model.Locacao;



@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
