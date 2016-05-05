package br.com.servicos;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import br.com.objetos.Questao;

@Path("/questao")
public class QuestaoService {
	
	public List<Questao> selecionaQuestoes(String codEvento){
		return new ArrayList<Questao>();
	}
	
}
