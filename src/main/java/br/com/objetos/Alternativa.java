package br.com.objetos;

public class Alternativa {

	private int codQuestao;
	private int codAlternativa;
	private String textoAlternativa;
	private boolean correta;
	
	public int getCodQuestao() {
		return codQuestao;
	}
	public void setCodQuestao(int codQuestao) {
		this.codQuestao = codQuestao;
	}
	public int getCodAlternativa() {
		return codAlternativa;
	}
	public void setCodAlternativa(int codAlternativa) {
		this.codAlternativa = codAlternativa;
	}
	public String getTextoAlternativa() {
		return textoAlternativa;
	}
	public void setTextoAlternativa(String textoAlternativa) {
		this.textoAlternativa = textoAlternativa;
	}
	public boolean isCorreta() {
		return correta;
	}
	public void setCorreta(boolean correta) {
		this.correta = correta;
	}
	
}
