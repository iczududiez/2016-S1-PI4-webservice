package br.com.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletContext;

public class GameStateManage {
	
	private Dictionary<String, GameState> gameStates = null;
	private Timestamp timeManage = null;
	
	public GameStateManage(){
		gameStates = new Hashtable<String, GameState>();
		timeManage = new Timestamp(new Date().getTime());
	}
	
	public static GameStateManage getGameStateManage(ServletContext context){
		
		GameStateManage gsm = null;
		
		if(context.getAttribute("gameStateManage") == null){
			gsm = new GameStateManage();
			context.setAttribute("gameStateManage", gsm);
		}else{
			gsm = (GameStateManage)context.getAttribute("gameStateManage");
		}
		
		return gsm;
	}
	
	public GameState getGameState(String identificador,Integer idEvento){
		
		GameState gs = gameStates.get(identificador);
		
		if(gs == null){
			gs = new GameState(identificador,idEvento,new Timestamp(new Date().getTime()));
			setGameState(identificador,gs);
		}
		
		lifeCicleGameState();
		
		return gs;
	}
	
	public GameState getGameState(String identificador){
		
		GameState gs = gameStates.get(identificador);
		lifeCicleGameState();
		return gs;
	}
	
	private void setGameState(String idEvento, GameState gs){
		gameStates.put(idEvento,gs);
	}
	
	@SuppressWarnings("deprecation")
	private void lifeCicleGameState(){
		Timestamp actualTime = new Timestamp(new Date().getTime());
		Boolean dayDiference = actualTime.getDay() != timeManage.getDay();
		Boolean minuteDiference = (((timeManage.getHours() * 60) + timeManage.getMinutes()) - ((actualTime.getHours() * 60) + actualTime.getMinutes())) > 30;
		if(dayDiference && (actualTime.getHours() >= 1)){
			// call Destroyer ClearGameState
			destroyGameState(actualTime);
			timeManage = new Timestamp(new Date().getTime());
		}else if(minuteDiference){
			//call ClearGameState
			destroyGameState(actualTime);
			timeManage = new Timestamp(new Date().getTime());
		}
	}
	
	@SuppressWarnings("deprecation")
	private void destroyGameState(Timestamp actualTime){
		Enumeration gsEnum = gameStates.elements();
		while(gsEnum.hasMoreElements()){
			GameState gs = (GameState)gsEnum.nextElement();
			Timestamp timeManage = gs.getTimeState();
			Boolean dayDiference = actualTime.getDay() != timeManage.getDay();
			if(dayDiference){
				gameStates.remove(gs.getIdEvento());
			}
		}
	}
}


