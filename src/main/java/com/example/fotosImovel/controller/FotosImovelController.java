package com.example.fotosImovel.controller;



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

import com.example.contratolocacao.model.ContratoLocacao;
import com.example.fotosImovel.model.FotosImovel;
import com.example.fotosImovel.repository.FotosImovelRepository;



@RestController
public class FotosImovelController {
	
	@Autowired
	private FotosImovelRepository fotosImovelRepository;
	
	
	
	public FotosImovelController(FotosImovelRepository fotosImovelRepository) {
		super();
		this.fotosImovelRepository = fotosImovelRepository;
	}

	@GetMapping("/listarFotosImovel")
	public List<FotosImovel> listarFotosImoveis(){
		return fotosImovelRepository.findAll();
		
		
	}
	
	@PostMapping(path="/salvarFotosImovel",consumes="application/json" )
	public ResponseEntity<FotosImovel> salvarFotosImovel(FotosImovel fotosImovel) {
		
		fotosImovelRepository.save(fotosImovel);
		
		
		return ResponseEntity.ok(fotosImovel);
				
	}
	
	
	
	
	
	@GetMapping("/cadastro/fotosImovel/{id}")
	public ResponseEntity<FotosImovel> buscarFotosImovelPorId(@PathVariable Long id) throws Exception{
		FotosImovel fotosImovel = fotosImovelRepository.getById(id);
		if(fotosImovel == null) {
			throw new Exception("Fotos do Imóvel nao encontrado pelo id" + id);
		}
		
		return ResponseEntity.ok(fotosImovel);
		
	}
	
	@PutMapping("/cadastro/fotosImovel/editar/{id}")
	public ResponseEntity<FotosImovel>  editarFotosImovelPorId(@PathVariable("id") Long id, @RequestBody @Valid FotosImovel fotosImovel) throws Exception{
		
		
		FotosImovel opcionalfotosImovel = fotosImovelRepository.getById(id);		
		if(opcionalfotosImovel == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		opcionalfotosImovel.setIdImovelFK(fotosImovel.getIdImovelFK());
		opcionalfotosImovel.setFotoInterna(fotosImovel.getFotoInterna());
		opcionalfotosImovel.setFotoExterna(fotosImovel.getFotoExterna());
		

		
		FotosImovel novoFotoImovel = fotosImovelRepository.save(opcionalfotosImovel);
		return ResponseEntity.ok(novoFotoImovel);
		
		
	}
	
	@DeleteMapping("/cadastro/fotosImovel/remover/{id}")
	public ResponseEntity<Map<String, Boolean>> removerFotosImovelPorId(@PathVariable Long id) throws Exception{
		FotosImovel fotosImovel = fotosImovelRepository.getById(id);
		if(fotosImovel == null) {
			throw new Exception("Fotos Do Imóvel nao encontrado" + id);
		}
		
		fotosImovelRepository.deleteById(id);
		Map<String, Boolean> responseAwait = new HashMap<>();
		responseAwait.put("Removido Com Sucesso", Boolean.TRUE);
		return ResponseEntity.ok(responseAwait);
	}


}
