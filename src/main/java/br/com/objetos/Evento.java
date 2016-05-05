package br.com.objetos;

public class Evento {

	private int codEvento;
	private String descricao;
	private String data;
	/*
	 * 1 = Questão assincrona
	 * 2 = Quem responder primeiro
	 * 3 = Todos Respondem
	 * 4 = Baseado no Master
	 */
	private int codTipoEvento;
	/*
	 * C = Criado
	 * A = Aberto
	 * E = Em execução
	 * F = Fechado
	 */
	private String codStatus;
	private int codProfessor;
	private String identificador;
	public int getCodEvento() {
		return codEvento;
	}
	public void setCodEvento(int codEvento) {
		this.codEvento = codEvento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getCodTipoEvento() {
		return codTipoEvento;
	}
	public void setCodTipoEvento(int codTipoEvento) {
		this.codTipoEvento = codTipoEvento;
	}
	public String getCodStatus() {
		return codStatus;
	}
	public void setCodStatus(String codStatus) {
		this.codStatus = codStatus;
	}
	public int getCodProfessor() {
		return codProfessor;
	}
	public void setCodProfessor(int codProfessor) {
		this.codProfessor = codProfessor;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
}
