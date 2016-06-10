package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

//url WebService
//tsitomcat.azurewebsites.net/eusei/rest/

@Path("/jogo")
public class GameService{
 
	@Context ServletContext contexto;
	private GameStateManage gsm;
	private GameState gs;
	
	public boolean gravaQuestaoGrupo(Questao questao, int codAlternativa, boolean correta){
		
		return true;
	}
	
	public static int selecionaCodGrupo(int idJogador,int codEvento) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		int result = 0;
		try{
			conn = Database.get().conn();
			psta = conn.prepareStatement("select ParticipanteGrupo.codGrupo from ParticipanteGrupo inner join Grupo on ParticipanteGrupo.codGrupo = Grupo.codGrupo where ParticipanteGrupo.codParticipante = ? and Grupo.codEvento = ?");
			psta.setInt(1,idJogador);
			psta.setInt(2,codEvento);
			
			ResultSet rs = psta.executeQuery();
			result = rs.getInt("codGrupo");
			
		}catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (psta != null)
				psta.close();
			if (conn != null)
				conn.close ();
		}
		
		return result;
	}
	
	@GET
	@Path("/ordemJogador/{identificador}/{idJogador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setOrdemjogador(@PathParam("identificador") String identificador, @PathParam("idJogador") String idJogador){
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			if(gs != null && gs.getEstadoDoJogo().equals("P")){
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
			if(gs != null && gs.getEstadoDoJogo().equals("R")){
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
		
		String ret = "";
		String codStatusEvento = "";
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			ret = gs.getEstadoDoJogo();
			if(ret.equals("AP")){
				ret = QuestaoService.temQuestao(identificador) ? "P" : ret;
			}
			codStatusEvento = EventoServices.statusEvento(identificador);
			if(codStatusEvento.equals("F")){
				ret = codStatusEvento;
			}
			
		} catch (Exception e) {
			return Response.status(500).entity(null).build();
		}
		
		return Response.status(200).entity(ret).build();
	}
	
	@GET
	@Path("/resposta/{identificador}/{idJogador}/{codAlternativa}/{valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reposta(@PathParam("identificador") String identificador, @PathParam("idJogador") String idJogador, @PathParam("codAlternativa") String codAlternativa, @PathParam("valor") String valor){
		
		Integer codGrupo,codParticipante,alternativa;
		
		try{
			gsm = GameStateManage.getGameStateManage(contexto);
			gs = gsm.getGameState(identificador);
			if(gs != null && gs.getEstadoDoJogo().equals("R")){
				if(valor == "1"){
					QuestaoService.atualizaQuestao("F", gs.getQuestaoAtual().getCodEvento(), gs.getQuestaoAtual().getCodQuestao());
					gs.setEstadoDoJogo("AP");
					gs.setQuestaoAtual(null);
				}else{
					List<Integer> jogadores = gs.getListaOrdemJogadores();
					int count = jogadores.size();
					if(count > 0){
						jogadores.remove(0);
						gs.setInicioResposta(null);
					}
					if(count == 0){
						gs.setEstadoDoJogo("AP");
						gs.setQuestaoAtual(null);
					}
				}
				
				Connection conn = null;
				PreparedStatement psta = null;
				
				codParticipante = Integer.parseInt(idJogador);
				alternativa = Integer.parseInt(codAlternativa);
				codGrupo = selecionaCodGrupo(codParticipante,gs.getQuestaoAtual().getCodEvento()); 
				
				conn = Database.get().conn();
				psta = conn.prepareStatement("insert into QuestaoGrupo(codQuestao, codAlternativa,codGrupo,correta) values (?,?,?,?)");
				psta.setInt(1, gs.getQuestaoAtual().getCodQuestao());
				psta.setInt(1, alternativa);
				psta.setInt(1, codGrupo);
				psta.setBoolean(1, valor.equals("1"));
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
			if(gs != null && gs.getEstadoDoJogo().equals("R")){
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
