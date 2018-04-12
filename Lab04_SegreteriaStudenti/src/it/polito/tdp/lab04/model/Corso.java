package it.polito.tdp.lab04.model;

public class Corso {
	private String nome;
	private String codice;
	private int ncred;
	private int pd;
	public Corso(String nome, String codice, int ncred, int pd) {
		this.nome = nome;
		this.codice = codice;
		this.ncred = ncred;
		this.pd = pd;
	}
	
	public String toString() {
		return nome;
	}

	public String getNome() {
		return nome;
	}

	public String getCodice() {
		return codice;
	}

	public int getNcred() {
		return ncred;
	}

	public int getPd() {
		return pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		Corso other = (Corso) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	


}
