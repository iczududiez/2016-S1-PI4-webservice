package br.com.objetos;

public class Evento {

	private int codEvento;
	private String descricao;
	private String data;
	private int codTipoEvento;
	private int codStatus;
	private int codGestor;
	
	public int getCodEvento() {
		return codEvento;
	}
	
	public void setCodEvento(int codEvento) {
		codEvento = codEvento;
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
		codTipoEvento = codTipoEvento;
	}
	
	public int getCodStatus() {
		return codStatus;
	}
	
	public void setCodStatus(int codStatus) {
		codStatus = codStatus;
	}
	
	public int getCodGestor() {
		return codGestor;
	}
	
	public void setCodGestor(int codGestor) {
		codGestor = codGestor;
	}
}
