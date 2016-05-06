package br.com.servicos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Path;

import br.com.objetos.Questao;
import br.com.senac.pi4.services.Database;

@Path("/questao")
public class QuestaoService {
	
	public Questao selecionaQuestoes(String codEvento) throws Exception{
		
		Connection conn = null;
		PreparedStatement psta = null;
		Questao questao = null;
		
		try{
			conn = Database.get().conn();		
			psta = conn.prepareStatement("select top 1 Questao.codQuestao, Questao.textoQuestao, Questao.codAssunto, Questao.codImagem, Questao.codTipoQuestao, Questao.codProfessor, Questao.ativo, Questao.dificuldade from QuestaoEvento inner join Questao on QuestaoEvento.codQuestao = Questao.codQuestao where QuestaoEvento.codEvento = ? and QuestaoEvento.codStatus = 'A'");
			Integer eID = null;
			eID = Integer.parseInt(codEvento);
			psta.setInt(1, eID);
			
			ResultSet rs = psta.executeQuery();
			rs.next();
			questao.setCodQuestao(rs.getInt("codQuestao"));
			
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
	
}
