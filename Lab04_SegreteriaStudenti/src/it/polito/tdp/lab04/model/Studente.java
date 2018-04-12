package it.polito.tdp.lab04.model;

public class Studente {
	private int matricola;
	private String cognome;
	private  String nome;
	private String cDS;
	public Studente(int matricola, String cognome, String nome, String cDS) {
		
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cDS = cDS;
	}
	
	
	public String toString() {
		return "Studente [matricola=" + matricola + ", cognome=" + cognome + ", nome=" + nome + ", cDS=" + cDS + "]";
	}


	public int getMatricola() {
		return matricola;
	}


	public String getCognome() {
		return cognome;
	}


	public String getNome() {
		return nome;
	}


	public String getcDS() {
		return cDS;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}
