package br.com.senac.pi4.services;

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
	
	public static Assunto selecionaAssunto(int codAssunto) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Assunto assunto = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select Assunto.codAssunto, Assunto.descricao, Area.descricao as descricaoArea from  Assunto inner join Area on Assunto.codArea = Area.codArea where codAssunto = ?");
			psta.setInt(1, codAssunto);
			
			ResultSet rs = psta.executeQuery();
			if(rs.next()){
				assunto = new Assunto();
				assunto.setCodAssunto(rs.getInt("codAssunto"));
				assunto.setDescricao(rs.getString("descricao"));
				assunto.setDescricaoArea(rs.getString("descricaoArea"));
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
		return assunto;
	}
}
