package br.com.objetos;

import java.util.List;

public class Questao {
	
	private int codQuestao;
	private String textoQuestao;
	private Assunto assunto;
	private Imagem imagem;
	//A = alternativa
	//T = Texto Objetivo
	//V = Verdadeiro e Falso
	private String codTipoQuestao;
	private int codProfessor;
	private boolean ativo;
	//F = Facil
	//M = Medio
	//D = Dificil
	private String dificuldade;
	private List<Alternativa> lstAlternativa;
	
	public int getCodQuestao() {
		return codQuestao;
	}
	public void setCodQuestao(int codQuestao) {
		this.codQuestao = codQuestao;
	}
	public String getTextoQuestao() {
		return textoQuestao;
	}
	public void setTextoQuestao(String textoQuestao) {
		this.textoQuestao = textoQuestao;
	}
	public Assunto getAssunto() {
		return assunto;
	}
	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}
	public Imagem getImagem() {
		return imagem;
	}
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	public String getCodTipoQuestao() {
		return codTipoQuestao;
	}
	public void setCodTipoQuestao(String codTipoQuestao) {
		this.codTipoQuestao = codTipoQuestao;
	}
	public int getCodProfessor() {
		return codProfessor;
	}
	public void setCodProfessor(int codProfessor) {
		this.codProfessor = codProfessor;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}
	public List<Alternativa> getLstAlternativa() {
		return lstAlternativa;
	}
	public void setLstAlternativa(List<Alternativa> lstAlternativa) {
		this.lstAlternativa = lstAlternativa;
	}
	public void addLstAlternativa(Alternativa alternativa){
		this.lstAlternativa.add(alternativa);
	}
}
