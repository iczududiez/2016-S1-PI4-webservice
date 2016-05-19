package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

@Path("/questao")
public class QuestaoService {
	
	@Context ServletContext contexto;
	private GameStateManage gsm;
	private GameState gs;
	
	public Questao selecionaQuestoes(String identificador) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Questao questao = null;
		
		try{
			conn = Database.get().conn();
			psta = conn.prepareStatement("select top 1 Questao.codQuestao, Questao.textoQuestao, Questao.codAssunto, Questao.codImagem, Questao.codTipoQuestao, Questao.codProfessor, Questao.ativo, Questao.dificuldade from QuestaoEvento inner join Questao on QuestaoEvento.codQuestao = Questao.codQuestao inner join Evento on QuestaoEvento.codEvento = Evento.codEvento where Evento.identificador = ?");
			psta.setString(1, identificador);
			
			ResultSet rs = psta.executeQuery();
			if(rs.next()){
				questao = new Questao();
				questao.setCodEvento(rs.getInt("codEvento"));
				questao.setCodQuestao(rs.getInt("codQuestao"));
				questao.setTextoQuestao(rs.getString("textoQuestao"));
				questao.setAssunto(AssuntoService.selecionaAssunto(rs.getInt("codAssunto")));
				questao.setImagem(ImagemService.selecionaImagem(rs.getInt("codImagem")));
				questao.setCodTipoQuestao(rs.getString("codTipoQuestao"));
				questao.setCodProfessor(rs.getInt("codProfessor"));
				questao.setAtivo(rs.getBoolean("ativo"));
				questao.setDificuldade(rs.getString("dificuldade"));
				questao.setLstAlternativa(AlternativaService.selecionaAlternativas(questao.getCodQuestao()));
			}
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

		return questao;
	}
	
	public static int atualizaQuestao(String status,int codEvento,int codQuestao) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		int result = 0;
		try{
			conn = Database.get().conn();
			psta = conn.prepareStatement("update QuestaoEvento set codStatus = ? where codEvento = ? and codQuestao = ?");
			psta.setString(1, status);
			psta.setInt(2, codEvento);
			psta.setInt(3, codQuestao);
			
			result = psta.executeUpdate();
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
	@Path("/{identificador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento(@PathParam("identificador") String identificador) {

		Questao questao = null;
		gsm = GameStateManage.getGameStateManage(contexto);
		
		
		try {
			gs = gsm.getGameState(identificador);
			if(gs == null){
				questao = selecionaQuestoes(identificador);
				if(questao != null){
					gs = gsm.getGameState(identificador,questao.getCodEvento());
					gs.setQuestaoAtual(questao);
				}
			}else{
				questao = gs.getQuestaoAtual();
				if(questao == null){
					questao = selecionaQuestoes(identificador);
					if(questao != null){
						gs = gsm.getGameState(identificador,questao.getCodEvento());
						gs.setQuestaoAtual(questao);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(null).build();	
		}
		if (questao == null){
			return Response.status(404).entity(null).build();
		}
		
		gsm = GameStateManage.getGameStateManage(contexto);
		gs = gsm.getGameState(identificador,questao.getCodEvento());
		
		try {
			if(atualizaQuestao("E",questao.getCodEvento(),questao.getCodQuestao()) > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Response.status(200).entity(questao).build();
	}
}
