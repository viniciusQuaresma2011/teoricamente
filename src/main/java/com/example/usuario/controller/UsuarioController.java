package com.example.usuario.controller;



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

import com.example.pessoa.model.Pessoa;
import com.example.usuario.model.Usuario;
import com.example.usuario.repository.UsuarioRepository;



@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/listarUsuario")
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
		
		
	}
	
	@PostMapping("/salvarUsuario")
	public void salvarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
				
	}
	
	
	
	@GetMapping("/cadastro/usuario/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) throws Exception{
		Usuario usuario = usuarioRepository.getById(id);
		if(usuario == null) {
			throw new Exception("Usuário nao encontrado pelo id" + id);
		}
		
		return ResponseEntity.ok(usuario);
		
	}
	
	@PutMapping("/cadastro/usuario/editar/{id}")
	public ResponseEntity<Usuario>  editarUsuarioPorId(@PathVariable("id") Long id, @RequestBody @Valid Usuario usuario) throws Exception{
		
		
		Usuario opcionalUsuario = usuarioRepository.getById(id);		
		if(opcionalUsuario == null) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		opcionalUsuario.setEmail(usuario.getEmail());
		opcionalUsuario.setSenha(usuario.getSenha());
		opcionalUsuario.setIdPessoa(usuario.getIdPessoa());
	
		
		Usuario novoUsuario = usuarioRepository.save(opcionalUsuario);
		return ResponseEntity.ok(novoUsuario);
		
		
	}
	
	@DeleteMapping("/cadastro/usuario/remover/{id}")
	public ResponseEntity<Map<String, Boolean>> removeUsuarioPeloId(@PathVariable Long id) throws Exception{
		Usuario usuario = usuarioRepository.getById(id);
		if(usuario == null) {
			throw new Exception("Usuario nao encontrado" + id);
		}
		
		usuarioRepository.deleteById(id);
		Map<String, Boolean> responseAwait = new HashMap<>();
		responseAwait.put("Removido Com Sucesso", Boolean.TRUE);
		return ResponseEntity.ok(responseAwait);
	}

}
