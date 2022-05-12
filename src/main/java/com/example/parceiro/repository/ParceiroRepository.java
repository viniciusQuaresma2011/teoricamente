package com.example.parceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.parceiro.model.Parceiro;



@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

}
