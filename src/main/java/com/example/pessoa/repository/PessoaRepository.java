package com.example.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pessoa.model.Pessoa;



@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
