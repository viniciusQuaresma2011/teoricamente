package com.example.imovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imovel.model.Imovel;



@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

}
