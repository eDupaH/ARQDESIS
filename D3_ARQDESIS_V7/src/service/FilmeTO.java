package service;

import java.util.ArrayList;

import models.Idioma;

public class FilmeTO {
	private int id;
	private String nome;
	private String genero;
	private ArrayList<Idioma> idioma;
	
	public FilmeTO(int id, String nome, String genero, ArrayList<Idioma> idioma) {
		super();
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.idioma = idioma;
	}
	
	public FilmeTO() {
		this.idioma = new ArrayList<Idioma>();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public ArrayList<Idioma> getIdioma() {
		return idioma;
	}
	
	public void setIdioma(ArrayList<Idioma> idioma) {
		this.idioma = idioma;
	}
	
}