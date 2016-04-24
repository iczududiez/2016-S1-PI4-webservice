package br.com.servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.objetos.Evento;
import br.com.objetos.Participante;
import br.com.senac.pi4.services.Database;

@Path("/evento")
public class EventoServices {
	
	public Evento selectEvento(int codEvento) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Evento evento = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select * from Evento where codEvento = ?");
			psta.setInt(1, codEvento);
			
			ResultSet rs = psta.executeQuery();
			
			evento = new Evento();
			evento.setCodEvento(rs.getInt("codEvento"));
			evento.setDescricao(rs.getString("Descricao"));
			evento.setData(rs.getString("data"));
			evento.setCodTipoEvento(rs.getInt("codTipoEvento"));
			evento.setCodStatus(rs.getInt("codStatus"));
			evento.setCodGestor(rs.getInt("codGestor"));
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
		return evento;
	}
	

	@GET
	@Path("/{codEvento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento(@PathParam("codEvento") int codEvento) {

		Evento evento = null;
		
		try {
			evento = selectEvento(codEvento);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (evento == null)
			return Response.status(404).entity(null).build();
		
		
		return Response.status(200).entity(evento).build();
	}
}
