package com.example.fotosImovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fotosImovel.model.FotosImovel;



@Repository
public interface FotosImovelRepository extends JpaRepository<FotosImovel, Long> {

}
