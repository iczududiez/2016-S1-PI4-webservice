package br.com.senac.pi4.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.controller.GameState;
import br.com.controller.GameStateManage;
import br.com.objetos.Questao;

/*
 * AP
 * P
 * AR
 * R
 */

/*
 * A
 * E
 */

@Path("/jogo")
public class GameService{
 
	@Context ServletContext contexto;
	private GameStateManage gsm;
	private GameState gs;
	
	public boolean gravaQuestaoGrupo(Questao questao, int codAlternativa, boolean correta){
		
		return true;
	}
	
	
	@GET
	@Path("/ordemJogador/{identificador}/{idJogador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setOrdemjogador(@PathParam("identificador") String identificador, @PathParam("idJogador") String idJogador){
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			if(gs != null && gs.getEstadoDoJogo() == "P"){
				if(gs.getListaOrdemJogadores().size() < 4 || !gs.expirouTempoEuSei()){
					if(gs.getQuestaoAtual() != null){
						gs.setListaOrdemJogadores(Integer.parseInt(idJogador));
					}
				}else{
					gs.setEstadoDoJogo("R");
					gs.setInicioEusei(null);
				}
			}
		} catch (Exception e) {
			//return Response.status(500).entity(null).build();
			e.printStackTrace();
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
			if(gs != null && gs.getEstadoDoJogo() == "R"){
				List<Integer> jogadores = gs.getListaOrdemJogadores();
				count = jogadores.size();
				if(count > 0){
					ret = (gs.getListaOrdemJogadores().get(0) == Integer.parseInt(idJogador));
					if(ret){
						if(gs.getInicioResposta() == null || gs.expirouTempoResposta()){
							gs.setInicioResposta(new Timestamp(new Date().getTime()));
						}
						if(gs.expirouTempoResposta()){
							gs.removeJogador(0);
						}
					}
				}else{
					gs.setEstadoDoJogo("AP");
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
		
		return Response.status(200).entity(ret).build();
	}
	
	@GET
	@Path("/estadoJogo/{identificador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstadoJogo(@PathParam("identificador") String identificador){
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		
		return Response.status(200).entity(gs.getEstadoDoJogo()).build();
	}
	
	@GET
	@Path("/resposta/{identificador}/{idJogador}/{codAlternativa}/{valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reposta(@PathParam("identificador") String identificador, @PathParam("idJogador") String idJogador, @PathParam("codAlternativa") String codAlternativa, @PathParam("valor") String valor){
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			if(gs != null && gs.getEstadoDoJogo() == "R"){
				List<Integer> jogadores = gs.getListaOrdemJogadores();
				int count = jogadores.size();
				if(count > 0){
					jogadores.remove(0);
					gs.setInicioResposta(null);
				}
				if(count == 0){
					gs.setEstadoDoJogo("AP");
				}
			}
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		
		return Response.status(200).entity(gs.getEstadoDoJogo()).build();
	}
	
	@GET
	@Path("/tempoEsgotado/{identificador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response tempoEsgotado(@PathParam("identificador") String identificador){
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			if(gs != null && gs.getEstadoDoJogo() == "R"){
				List<Integer> jogadores = gs.getListaOrdemJogadores();
				int count = jogadores.size();
				if(count > 0){
					jogadores.remove(0);
					gs.setInicioResposta(null);
				}
				if(count == 0){
					gs.setEstadoDoJogo("AP");
				}
			}
			
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		
		return Response.status(200).entity(gs.getEstadoDoJogo()).build();
	}
}
