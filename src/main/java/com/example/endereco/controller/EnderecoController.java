package com.example.endereco.controller;



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
import com.example.endereco.model.Endereco;
import com.example.endereco.repository.EnderecoRepository;



@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	
	public EnderecoController(EnderecoRepository enderecoRepository) {
		super();
		this.enderecoRepository = enderecoRepository;
	}

	@GetMapping("/listarEnderecos")
	public List<Endereco> listarEnderecos(){
		return enderecoRepository.findAll();
		
		
	}
	
	@PostMapping("/salvarEndereco")
	public void salvarEndereco(Endereco endereco) {
		enderecoRepository.save(endereco);
				
	}
	
	
	@GetMapping("/cadastro/endereco/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) throws Exception{
		Endereco endereco = enderecoRepository.getById(id);
		if(endereco == null) {
			throw new Exception("Endereco nao encontrado pelo id" + id);
		}
		
		return ResponseEntity.ok(endereco);
		
	}
	
	@PutMapping("/cadastro/endereco/editar/{id}")
	public ResponseEntity<Endereco>  editarEnderecoPorId(@PathVariable("id") Long id, @RequestBody @Valid Endereco endereco) throws Exception{
		
		
		Endereco opcionalendereco = enderecoRepository.getById(id);		
		if(opcionalendereco == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		opcionalendereco.setLogradouro(endereco.getLogradouro());
		opcionalendereco.setBairro(endereco.getBairro());
		opcionalendereco.setPontoReferencia(endereco.getPontoReferencia());
		opcionalendereco.setCep(endereco.getCep());
		opcionalendereco.setIdPessoaFK(endereco.getIdPessoaFK());
		opcionalendereco.setNumero(endereco.getNumero());
		opcionalendereco.setComplemento(endereco.getComplemento());
		opcionalendereco.setUf(endereco.getUf());
		opcionalendereco.setNacionalidade(endereco.getNacionalidade());
		
		
		

		
		Endereco novoEndereco = enderecoRepository.save(opcionalendereco);
		return ResponseEntity.ok(novoEndereco);
		
		
	}
	
	@DeleteMapping("/cadastro/endereco/remover/{id}")
	public ResponseEntity<Map<String, Boolean>> RemoverEnderecoPorId(@PathVariable Long id) throws Exception{
		Endereco endereco = enderecoRepository.getById(id);
		if(endereco == null) {
			throw new Exception("Endereco do Imóvel nao encontrado" + id);
		}
		
		enderecoRepository.deleteById(id);
		Map<String, Boolean> responseAwait = new HashMap<>();
		responseAwait.put("Removido Com Sucesso", Boolean.TRUE);
		return ResponseEntity.ok(responseAwait);
	}
}
