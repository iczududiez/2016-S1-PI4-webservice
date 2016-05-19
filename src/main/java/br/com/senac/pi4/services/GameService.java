package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.controller.GameState;
import br.com.controller.GameStateManage;

import com.google.gson.Gson;

@Path("/jogo")
public class GameService{
 
	@Context ServletContext contexto;
	private GameStateManage gsm;
	private GameState gs;
	
	@GET
	@Path("/ordemJogador/{identificador}/{idJogador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setOrdemjogador(@PathParam("identificador") String identificador, @PathParam("idJogador") String idJogador){
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			gs.setListaOrdemJogadores(Integer.parseInt(idJogador));
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if(gs == null){
			return Response.status(404).entity(null).build();
		}
		
		return Response.status(200).entity(null).build();
	}
	
	@GET
	@Path("/minhaVez/{identificador}/{idJogador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response minhaVez(@PathParam("identificador") String identificador, @PathParam("idJogador") String idJogador){
		
		boolean ret = false;
		int count = 0;
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			if(gs != null){
				List<Integer> jogadores = gs.getListaOrdemJogadores();
				count = jogadores.size();
				if(count > 0){
					ret = (gs.getListaOrdemJogadores().get(0) == Integer.parseInt(idJogador));
					if(ret){
						gs.removeJogador(0);
					}
				}
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		if(gs == null){
			return Response.status(404).entity(null).build();
		}else if(count == 0){
			return Response.status(400).entity(null).build();
		}else if(!ret){
			return Response.status(100).entity(null).build();
		}
		
		return Response.status(200).entity(null).build();
	}
}
