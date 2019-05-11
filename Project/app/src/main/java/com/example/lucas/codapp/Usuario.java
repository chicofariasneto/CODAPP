package com.example.lucas.codapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	private String nome;
	private String usuario;
	private String senha;
	private String cpf;
	private String sangue;
	private String sexo;
	private String nascimento;
	private String cep;

	public Usuario(String nome, String usuario, String cpf, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.cpf = cpf;
		this.senha = senha;
		sangue = "";
		sexo = "";
		nascimento = "";
		cep = "";
	}

	public void setNome(String name) {
		nome = name;
	}
	public void setUsuario(String em) {
		usuario = em;
	}
	public void setCPF(String cp) {
		cpf = cp;
	}
	public void setSenha(String pass) {
		senha = pass;
	}
	public void setSangue(String san) {
		sangue = san;
	}
	public void setSexo(String sex) {
		sexo = sex;
	}
	public void setNascimento(String nasc) {
		nascimento = nasc;
	}
	public void setCEP(String cp) {
		cep = cp;
	}

	public String getNome() {
		return nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getCPF() {
		return cpf;
	}
	public String getSenha() {
		return senha;
	}
	public String getSangue() {
		return sangue;
	}
	public String getNascimento() {
		return nascimento;
	}
	public String getCEP() {
		return cep;
	}
	public String getSexo() {
		return sexo;
	}
	public ArrayList<String> getDadosGerais() {
		ArrayList<String> dados = new ArrayList<String>();
		dados.add(nome);
		dados.add(usuario);
		dados.add(cpf);
		dados.add(sangue);
		dados.add(nascimento);
		dados.add(cep);
		dados.add(sexo);
		return dados;
	}
}