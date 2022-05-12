package com.example.documentoImovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.documentoImovel.movel.DocumentoImovel;



@Repository
public interface DocumentoImovelRepository extends JpaRepository<DocumentoImovel, Long> {

}
