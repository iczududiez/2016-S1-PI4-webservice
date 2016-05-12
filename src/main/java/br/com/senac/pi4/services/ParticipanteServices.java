package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.objetos.Participante;
import br.com.senac.pi4.services.Database;
import br.com.senac.pi4.services.ParticipanteGrupo;


@Path("/participante")
public class ParticipanteServices {
	
	public Participante selectParticipante(String login,byte[] senha) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Participante participante = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select * from Participante where email = ? and senha = ? and ativo = 1");
			psta.setString(1, login);
			psta.setBytes(2, senha);

			System.out.print(login);
			System.out.print(senha);
			
			ResultSet rs = psta.executeQuery();
			
			participante = new Participante();
			participante.setCodParticipante(rs.getInt("codParticipante"));
			participante.setNmParticipante(rs.getString("nmParticipante"));
			participante.setCodParticipante(rs.getInt("codCurso"));
			participante.setEmail(rs.getString("email"));
			participante.setSenha(rs.getString("senha"));
			participante.setAtivo(rs.getBoolean("ativo"));
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
	public Response getParticipante(@PathParam("login") String login, @PathParam("senha") byte[] senha) {

		Participante participante = null;
		
		try {
			participante = selectParticipante(login, senha);
		} catch (Exception e) {
			return Response.status(500).entity(null).build();	
		}
		if (participante == null){
			return Response.status(404).entity(null).build();
		}
		
		
		return Response.status(200).entity(participante).build();
	}
	

	@GET
	@Path("/teste/{teste}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getParticipante(@PathParam("teste") String login) {

		
		return Response.status(200).entity(login).build();
	}
	
	
}
