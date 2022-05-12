package com.example.locacao.controller;



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
import com.example.locacao.model.Locacao;
import com.example.locacao.repository.LocacaoRepository;



@RestController
public class LocacaoController {
	
	@Autowired
	private LocacaoRepository locacaoRepository;
	
	
	
	public LocacaoController(LocacaoRepository locacaoRepository) {
		super();
		this.locacaoRepository = locacaoRepository;
	}

	@GetMapping("/listarLocacoes")
	public List<Locacao> listarLocacoes(){
		return locacaoRepository.findAll();
		
		
	}
	
	@PostMapping("/salvarLocacao")
	public Locacao salvarLocacao(Locacao locacao) {
		return locacaoRepository.save(locacao);
				
	}
	
	
	
	
	
	@GetMapping("/cadastro/locacao/{id}")
	public ResponseEntity<Locacao> buscarLocacaoPorId(@PathVariable Long id) throws Exception{
		Locacao locacao = locacaoRepository.getById(id);
		if(locacao == null) {
			throw new Exception("Locação nao encontrada pelo id" + id);
		}
		
		return ResponseEntity.ok(locacao);
		
	}
	
	@PutMapping("/cadastro/locacao/editar/{id}")
	public ResponseEntity<Locacao>  editarLocacaoPorId(@PathVariable("id") Long id, @RequestBody @Valid Locacao locacao) throws Exception{
		
		
		Locacao opcionalLocacao = locacaoRepository.getById(id);		
		if(opcionalLocacao == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		opcionalLocacao.setIdUsuarioFK(locacao.getIdUsuarioFK());
		opcionalLocacao.setIdImovelFK(locacao.getIdImovelFK());
		opcionalLocacao.setIdContratoLocacaoFK(locacao.getIdContratoLocacaoFK());
		opcionalLocacao.setStatusLocacao(locacao.getStatusLocacao());
		opcionalLocacao.setQuantPessoa(locacao.getQuantPessoa());
		
		

		
		Locacao novoLocacao = locacaoRepository.save(opcionalLocacao);
		return ResponseEntity.ok(novoLocacao);
		
		
	}
	
	@DeleteMapping("/cadastro/locacao/remover/{id}")
	public ResponseEntity<Map<String, Boolean>> removerLocacaoPorId(@PathVariable Long id) throws Exception{
		Locacao locacao = locacaoRepository.getById(id);
		if(locacao == null) {
			throw new Exception("Locação nao encontrado" + id);
		}
		
		locacaoRepository.deleteById(id);
		Map<String, Boolean> responseAwait = new HashMap<>();
		responseAwait.put("Removido Com Sucesso", Boolean.TRUE);
		return ResponseEntity.ok(responseAwait);
	}

}
