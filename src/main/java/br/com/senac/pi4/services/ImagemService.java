package br.com.senac.pi4.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.objetos.Imagem;
import br.com.senac.pi4.services.Database;

public class ImagemService {
	
	public static Imagem selecionaImagem(int codImagem) throws Exception{
		Connection conn = null;
		PreparedStatement psta = null;
		Imagem imagem = null;
		try {
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select Assunto.codAssunto, Assunto.descricao, Area.descricao as descricaoArea from  Assunto inner join Area on Assunto.codArea = Area.codArea where codAssunto = ?");
			psta.setInt(1, codImagem);
			
			ResultSet rs = psta.executeQuery();
			if(rs.next()){
				imagem = new Imagem();
				imagem.setCodImagem(rs.getInt("codImagem"));
				imagem.setTituloImagem(rs.getString("tituloImagem"));
				imagem.setBitmapImagem(rs.getBytes("bitmapImagem"));
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
		
		return imagem;
	}
}
