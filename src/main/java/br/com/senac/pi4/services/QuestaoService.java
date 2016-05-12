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
import br.com.objetos.Evento;
import br.com.objetos.Questao;
import br.com.senac.pi4.services.Database;

@Path("/questao")
public class QuestaoService {
	
	@Context ServletContext contexto;
	private GameStateManage gsm = GameStateManage.getGameStateManage(contexto);
	
	public Questao selecionaQuestoes(String identificador) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Questao questao = null;
		
		try{
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select top 1 Questao.codQuestao, Questao.textoQuestao, Questao.codAssunto, Questao.codImagem, Questao.codTipoQuestao, Questao.codProfessor, Questao.ativo, Questao.dificuldade from QuestaoEvento inner join Questao on QuestaoEvento.codQuestao = Questao.codQuestao inner join Evento on QuestaoEvento.codEvento = Evento.codEvento where Evento.identificador = ? and QuestaoEvento.codStatus = 'A'");
			psta.setString(1, identificador);
			
			ResultSet rs = psta.executeQuery();
			if(rs.next()){
				questao = new Questao();
				questao.setCodQuestao(rs.getInt("codQuestao"));
				questao.setTextoQuestao(rs.getString("textQestao"));
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

		return new Questao();
	}
	
	public void atualizaQuestao(String Status) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		try{
			conn = Database.get().conn();
			psta = conn.prepareStatement("");
			psta.executeUpdate();
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
	}
	
	@GET
	@Path("/{identificador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento(@PathParam("codEvento") String identificador) {

		Questao questao = null;
		
		try {
			questao = selecionaQuestoes(identificador);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (questao == null){
			return Response.status(404).entity(null).build();
		}
		
		GameState gs = gsm.getGameState(identificador);
		gs.setQuestao(questao);
		
		return Response.status(200).entity(questao).build();
	}
}
