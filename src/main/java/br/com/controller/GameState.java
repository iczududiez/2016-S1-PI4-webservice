package br.com.controller;

import java.sql.Timestamp;

public class GameState {
	
	public GameState(String idEvento,Timestamp timeState){
		this.idEvento = idEvento;
		this.timeState = timeState;
	}

	private Timestamp timeState;
	private String idEvento;
	private int codCategoria;
	
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

}
