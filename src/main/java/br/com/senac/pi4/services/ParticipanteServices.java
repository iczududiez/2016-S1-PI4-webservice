package br.com.senac.pi4.services;

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

import br.com.objetos.Participante;


@Path("/participante")
public class ParticipanteServices {
	
	public Participante selectParticipante(String login,String senha) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Participante participante = null;
		try {
			conn = Database.get().conn();	
			//String sql = "select * from Participante where email = '"+login + "' and senha = HASHBYTES('SHA1', '"+senha+"') and ativo = 1";
			psta = conn.prepareStatement("select * from Participante where email = ? and senha = HASHBYTES('SHA1', convert(varchar, ?)) and ativo = 1");
			psta.setString(1, login);
			psta.setString(2, senha);
			
			ResultSet rs = psta.executeQuery();
			if(rs.next()){
				participante = new Participante();
				participante.setCodParticipante(rs.getInt("codParticipante"));
				participante.setNmParticipante(rs.getString("nmParticipante"));
				participante.setCodCurso(rs.getInt("codCurso"));
				participante.setEmail(rs.getString("email"));
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
		return participante;
	}
	
	@GET
	@Path("/{login}/{senha}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getParticipante(@PathParam("login") String login, @PathParam("senha") String senha) {

		Participante participante = null;
		
		try {
			participante = selectParticipante(login, senha);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(null).build();	
		}
		if (participante == null){
			return Response.status(404).entity(null).build();
		}
		
		
		return Response.status(200).entity(participante).build();
	}
}
