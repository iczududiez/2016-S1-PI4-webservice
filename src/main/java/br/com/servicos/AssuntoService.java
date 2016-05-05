package br.com.servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import br.com.controller.GameStateManage;
import br.com.objetos.Assunto;
import br.com.senac.pi4.services.Database;

public class AssuntoService {
	
	@Context ServletContext contexto;
	private GameStateManage gsm = GameStateManage.getGameStateManage(contexto);
	
	public Assunto selecionaAssunto(int codAssunto) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Assunto assunto = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select * from Assunto where codAssunto = ?");
			psta.setInt(1, codAssunto);
			
			ResultSet rs = psta.executeQuery();
			
			assunto = new Assunto();
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
		return assunto;
	}
}
