package com.example.localizacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.locacao.model.Locacao;
import com.example.localizacao.model.Localizacao;



@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {

}
