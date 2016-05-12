package br.com.controller;

import java.sql.Timestamp;

import br.com.objetos.Questao;

public class GameState {
	
	public GameState(String idEvento,Timestamp timeState){
		this.idEvento = idEvento;
		this.timeState = timeState;
	}

	private Timestamp timeState;
	private String idEvento;
	private Questao questaoAtual;
	
	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public Timestamp getTimeState() {
		return timeState;
	}

	public void setTimeState(Timestamp timeState) {
		this.timeState = timeState;
	}
	
	public Questao getQuestao(){
		return this.questaoAtual;
	}
	
	public void setQuestao(Questao questao){
		this.questaoAtual = questao;
	}
}
