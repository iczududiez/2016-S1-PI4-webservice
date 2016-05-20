package br.com.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.objetos.Questao;

public class GameState {
	
	public GameState(String identificador,int idEvento,Timestamp timeState){
		this.identificador = identificador;
		this.idEvento = idEvento;
		this.timeState = timeState;
		this.listaOrdemJogadores = new ArrayList<Integer>();
		this.tempoDeResposta = 30000;
		this.estadoDoJogo = "AP";
	}

	private Timestamp timeState;
	private String identificador;
	private int idEvento;
	private Questao questaoAtual;
	private String statusEvento;
	private List<Integer> listaOrdemJogadores;
	public Timestamp getInicioEusei() {
		return inicioEusei;
	}

	public void setInicioEusei(Timestamp inicioEusei) {
		this.inicioEusei = inicioEusei;
	}

	public String getEstadoDoJogo() {
		return estadoDoJogo;
	}

	public void setEstadoDoJogo(String estadoDoJogo) {
		this.estadoDoJogo = estadoDoJogo;
	}

	private int tempoDeResposta;
	private Timestamp inicioResposta;
	private Timestamp inicioEusei;
	private String estadoDoJogo;
	
	public int getTempoDeResposta() {
		return tempoDeResposta;
	}

	public void setTempoDeResposta(int tempoDeResposta) {
		this.tempoDeResposta = tempoDeResposta;
	}

	public Timestamp getInicioResposta() {
		return inicioResposta;
	}

	public void setInicioResposta(Timestamp inicioResposta) {
		this.inicioResposta = inicioResposta;
	}

	public List<Integer> getListaOrdemJogadores() {
		return listaOrdemJogadores;
	}

	public void setListaOrdemJogadores(int jogador) {
		if(!this.listaOrdemJogadores.contains(jogador)){
			this.listaOrdemJogadores.add(jogador);
		}
	}
	
	public void removeJogador(int index){
		if(this.listaOrdemJogadores.size() > 0){
			this.listaOrdemJogadores.remove(index);
		}
	}

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
	
	public boolean expirouTempoEuSei(){
		return this.getInicioEusei() != null && (new Date().getTime() - this.getInicioResposta().getTime()) > this.getTempoDeResposta();
	}
	
	public boolean expirouTempoResposta(){
		return this.getInicioResposta() != null && (new Date().getTime() - this.getInicioResposta().getTime()) > this.getTempoDeResposta();
	}
	
}
