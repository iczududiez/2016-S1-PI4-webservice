package br.com.senac.pi4.services;

public class ParticipanteGrupo {
	
	private Integer codGrupo;
	private String nmGrupo;
	private Integer codParticipante;
	private String nmParticipante;
	private Integer codLider;
	private Integer grupoFinalizado;
	
	public Integer getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(Integer codGrupo) {
		this.codGrupo = codGrupo;
	}
	public String getNmGrupo() {
		return nmGrupo;
	}
	public void setNmGrupo(String nmGrupo) {
		this.nmGrupo = nmGrupo;
	}
	public Integer getCodParticipante() {
		return codParticipante;
	}
	public void setCodParticipante(Integer codParticipante) {
		this.codParticipante = codParticipante;
	}
	public String getNmParticipante() {
		return nmParticipante;
	}
	public void setNmParticipante(String nmParticipante) {
		this.nmParticipante = nmParticipante;
	}
	public Integer getCodLider() {
		return codLider;
	}
	public void setCodLider(Integer codLider) {
		this.codLider = codLider;
	}
	public Integer getGrupoFinalizado() {
		return grupoFinalizado;
	}
	public void setGrupoFinalizado(Integer grupoFinalizado) {
		this.grupoFinalizado = grupoFinalizado;
	}
	
	
	
}
