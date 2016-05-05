package br.com.objetos;

import java.util.BitSet;

public class Imagem {
	
	private int codImagem;
	private String tituloImagem;
	private byte[] bitmapImagem;
	
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
	public byte[] getBitmapImagem() {
		return bitmapImagem;
	}
	public void setBitmapImagem(byte[] bitmapImagem) {
		this.bitmapImagem = bitmapImagem;
	}
	
}
