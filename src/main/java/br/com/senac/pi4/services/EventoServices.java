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

import br.com.controller.GameStateManage;
import br.com.objetos.Evento;
import br.com.senac.pi4.services.Database;

@Path("/evento")
public class EventoServices {
	
	@Context ServletContext contexto;
	private GameStateManage gsm = GameStateManage.getGameStateManage(contexto);
	
	public Evento selectEvento(String idEvento) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Evento evento = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select * from Evento where identificador = ?");
			psta.setString(1, idEvento);
			
			ResultSet rs = psta.executeQuery();
			if(rs.next()){
				evento = new Evento();
				evento.setCodEvento(rs.getInt("codEvento"));
				evento.setDescricao(rs.getString("Descricao"));
				evento.setData(rs.getString("data"));
				evento.setCodTipoEvento(rs.getInt("codTipoEvento"));
				evento.setCodStatus(rs.getString("codStatus"));
				evento.setCodProfessor(rs.getInt("codProfessor"));
				evento.setIdentificador(rs.getString("identificador"));
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
		return evento;
	}
	
	public String statusEvento(String identificador) throws Exception{
		Connection conn = null;
		PreparedStatement psta = null;
		String codEvento = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select codStatus from Evento where identificador = ?");
			psta.setString(1, identificador);
			
			ResultSet rs = psta.executeQuery();
			rs.next();
			codEvento = rs.getString("codStatus");
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
		
		return codEvento;
	}
	
	@GET
	@Path("/{idEvento}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvento(@PathParam("idEvento") String idEvento) {

		Evento evento = null;
		
		try {
			evento = selectEvento(idEvento);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (evento == null){
			return Response.status(404).entity(null).build();
		}else if(evento.getCodStatus() == "F"){
			return Response.status(401).entity(null).build();
		}
		
		gsm.getGameState(idEvento);
		
		return Response.status(200).entity(evento).build();
	}
	
	@GET
	@Path("/codStatus/{identificador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCodStatus(@PathParam("identificador") String identificador){

		String codEvento = null;
		try {
			codEvento = statusEvento(identificador);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (codEvento == null){
			return Response.status(404).entity(null).build();
		}
		
		return Response.status(200).entity(codEvento).build();
	}
}
