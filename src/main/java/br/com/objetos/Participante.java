package br.com.objetos;

public class Participante {

	private int codParticipante;
	private String nmParticipante;
	private int codCurso;
	private String email;
	private String senha;
	private String sexo;
	private String status;
	
	public int getCodParticipante() {
		return codParticipante;
	}
	public void setCodParticipante(int codParticipante) {
		this.codParticipante = codParticipante;
	}
	public String getNmParticipante() {
		return nmParticipante;
	}
	public void setNmParticipante(String nmParticipante) {
		this.nmParticipante = nmParticipante;
	}
	public int getCodCurso() {
		return codCurso;
	}
	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
