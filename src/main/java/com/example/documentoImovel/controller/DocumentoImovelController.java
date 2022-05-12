package com.example.documentoImovel.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.documentoImovel.movel.DocumentoImovel;
import com.example.documentoImovel.repository.DocumentoImovelRepository;



@RestController
public class DocumentoImovelController {
	
	@Autowired
	private DocumentoImovelRepository documentoImovelRepository;
	
	
	
	public DocumentoImovelController(DocumentoImovelRepository documentoImovelRepository) {
		super();
		this.documentoImovelRepository = documentoImovelRepository;
	}

	@GetMapping("/listarDocumentoImoveis")
	public List<DocumentoImovel> listarDocumentoImoveis(){
		return documentoImovelRepository.findAll();
		
		
	}
	
	@PostMapping("/salvarDocumentoImovel")
	public void salvarDocumentoImovel(DocumentoImovel documentoImovel) {
		documentoImovelRepository.save(documentoImovel);
				
	}
	
	
	@GetMapping("/cadastro/documentoImovel/{id}")
	public ResponseEntity<DocumentoImovel> buscarDocumentoImovelPorId(@PathVariable Long id) throws Exception{
		DocumentoImovel documentoImovel = documentoImovelRepository.getById(id);
		if(documentoImovel == null) {
			throw new Exception("Documento do Imóvel nao encontrado pelo id" + id);
		}
		
		return ResponseEntity.ok(documentoImovel);
		
	}
	
	@PutMapping("/cadastro/documentoImovel/editar/{id}")
	public ResponseEntity<DocumentoImovel>  editarDocumentoImovelPorId(@PathVariable("id") Long id, @RequestBody @Valid DocumentoImovel documentoImovel) throws Exception{
		
		
		DocumentoImovel opcionaldocumentoImovel = documentoImovelRepository.getById(id);		
		if(opcionaldocumentoImovel == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		opcionaldocumentoImovel.setNumCertidao(documentoImovel.getNumCertidao());
		opcionaldocumentoImovel.setIdImovelFK(documentoImovel.getIdImovelFK());
		
		

		
		DocumentoImovel novoDocumentoImovel = documentoImovelRepository.save(opcionaldocumentoImovel);
		return ResponseEntity.ok(novoDocumentoImovel);
		
		
	}
	
	@DeleteMapping("/cadastro/documentoImovel/remover/{id}")
	public ResponseEntity<Map<String, Boolean>> removerDocumentoImovelPorId(@PathVariable Long id) throws Exception{
		DocumentoImovel documentoImovel = documentoImovelRepository.getById(id);
		if(documentoImovel == null) {
			throw new Exception("Documento do Imóvel nao encontrado" + id);
		}
		
		documentoImovelRepository.deleteById(id);
		Map<String, Boolean> responseAwait = new HashMap<>();
		responseAwait.put("Removido Com Sucesso", Boolean.TRUE);
		return ResponseEntity.ok(responseAwait);
	}

}
