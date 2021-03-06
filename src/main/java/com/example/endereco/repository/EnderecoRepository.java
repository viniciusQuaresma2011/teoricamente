package com.example.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.endereco.model.Endereco;
import com.example.pessoa.model.Pessoa;



@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
