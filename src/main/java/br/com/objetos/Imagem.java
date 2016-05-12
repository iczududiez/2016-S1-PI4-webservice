package br.com.objetos;

import java.nio.charset.StandardCharsets;

public class Imagem {
	
	private int codImagem;
	private String tituloImagem;
	private String bitmapImagem;
	
	public int getCodImagem() {
		return codImagem;
	}
	public void setCodImagem(int codImagem) {
		this.codImagem = codImagem;
	}
	public String getTituloImagem() {
		return tituloImagem;
	}
	public void setTituloImagem(String tituloImagem) {
		this.tituloImagem = tituloImagem;
	}
	public String getBitmapImagem() {
		return bitmapImagem;
	}
	public void setBitmapImagem(byte[] bitmapImagem) {
		this.bitmapImagem = new String(bitmapImagem, StandardCharsets.UTF_8);;
	}
	
}
