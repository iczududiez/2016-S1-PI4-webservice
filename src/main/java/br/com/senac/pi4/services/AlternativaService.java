package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.controller.GameStateManage;
import br.com.objetos.Alternativa;
import br.com.senac.pi4.services.Database;

//@Path("/alternativa")
public class AlternativaService {
	
	public static List<Alternativa> selecionaAlternativas(int codQuestao) throws Exception{
		
		List<Alternativa> lstAlternativa = new ArrayList<Alternativa>();
		Connection conn = null;
		PreparedStatement psta = null;			
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select * from Alternativa where codQuestao = ?");
			psta.setInt(1, codQuestao);
			
			ResultSet rs = psta.executeQuery();
			
			while (rs.next()) {
				Alternativa alternativa = new Alternativa();
				alternativa.setCodAlternativa(rs.getInt("codAlternativa"));
				alternativa.setCodQuestao(rs.getInt("codQuestao"));
				alternativa.setTextoAlternativa(rs.getString("textoAlternativa"));
				alternativa.setCorreta(rs.getBoolean("correta"));
				lstAlternativa.add(alternativa);
			}
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			if (psta != null)
				psta.close();
			if (conn != null)
				conn.close ();
		}
		return lstAlternativa;
	}
	/*
	@GET
	@Path("/{codQuestao}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAlaternativa(@PathParam("codQuestao") int codQuestao){
		
		List<Alternativa> listPg = null;
		
		try {
			listPg = selecionaAlternativas(codQuestao);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (listPg == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(listPg).build();
	}
	*/
}
