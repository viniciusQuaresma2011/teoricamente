package com.example.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.example.pessoa.model.Pessoa;

@Table
@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column
	private String email;
	
	@Column
	private String senha;
	
	@OneToOne
	@JoinColumn
	@PrimaryKeyJoinColumn
	private Pessoa pessoa;
	
	

	public Usuario() {
		super();
	}

	public Usuario(Long idUsuario, String email, String senha, Pessoa pessoa) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.senha = senha;
		this.pessoa = pessoa;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getIdPessoa() {
		return pessoa;
	}

	public void setIdPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
	
	
	

	
