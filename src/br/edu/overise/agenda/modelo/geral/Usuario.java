package br.edu.overise.agenda.modelo.geral;

import java.awt.Color;

public class Usuario implements ITabelaBD {
	private int cod;
	private String nome, senha, avatar;
	Color cor;
	public int getCod() {
		return cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario(int c){
		this.cod = c;
	}
	
	public Usuario(){}
	
	@Override public String toString() {
		return "Código: \t" + getCod() + "\n" +
				"Nome: \t\t" + getNome() + "\n" +
				"Senha: \t\t" + getSenha() + "\n";
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Color getCor() {
		return cor;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
}
