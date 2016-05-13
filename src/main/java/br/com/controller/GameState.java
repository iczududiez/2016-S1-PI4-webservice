package br.com.controller;

import java.sql.Timestamp;

import br.com.objetos.Questao;

public class GameState {
	
	public GameState(String identificador,int idEvento,Timestamp timeState){
		this.identificador = identificador;
		this.idEvento = idEvento;
		this.timeState = timeState;
	}

	private Timestamp timeState;
	private String identificador;
	private int idEvento;
	private Questao questaoAtual;
	private String statusEvento; 
	
	public String getStatusEvento() {
		return statusEvento;
	}

	public void setStatusEvento(String statusEvento) {
		this.statusEvento = statusEvento;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Timestamp getTimeState() {
		return timeState;
	}

	public void setTimeState(Timestamp timeState) {
		this.timeState = timeState;
	}
	
	public Questao getQuestaoAtual(){
		return this.questaoAtual;
	}
	
	public void setQuestaoAtual(Questao questao){
		this.questaoAtual = questao;
	}
}
