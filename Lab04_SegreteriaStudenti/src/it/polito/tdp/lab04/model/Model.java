package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private List<Corso> corsi;
	private List<Studente> studenti;
	
	private CorsoDAO CDAO;
	private StudenteDAO SDAO;
	
	
	public Model()
	{
		CDAO=new CorsoDAO();
		SDAO=new StudenteDAO();
		corsi=CDAO.getTuttiICorsi();
		studenti=SDAO.getTuttiStudenti();
	}
	
	public boolean iscrivi(Corso corso, int matricola)
	{
		Studente studente=null;
		for(Studente s:studenti)
		{
			if(s.getMatricola()==matricola)
			{
				studente=s;
				break;
			}
		}
		
		return CDAO.iscriviStudenteACorso(studente, corso);
	}
	
	public List<Corso> listaCorsi()
	{
		
		
		return corsi;
		
	}
	
	public List<Studente> listaStudenti()
	{
		
		return studenti;
		
	}
	
	public List<Studente> getIscritti(Corso corso)
	{
		return CDAO.getStudentiIscrittiAlCorso(corso);
		
	}
	
	public List<Corso> getCorsiStudente(int matricola)
	{
		
		for(Studente s:studenti)
		{
			if(s.getMatricola()==matricola)
			{
				
				return SDAO.getCorsiDiStudente(s);
			}
		}
		return null;
		
	}
	
	public boolean iscrittoCorso(int matricola, String codiceCorso)
	{
		Corso corso=null;
		boolean flag=false;
		for(Corso c:corsi)
		{
			if(c.getCodice().equals(codiceCorso))
			{
				corso=c;
				break;
			}	
		}
		if(corso != null) {
		List<Studente>listaTmp=this.getIscritti(corso);
		
		for(Studente s:listaTmp)
		{
			if(s.getMatricola()==matricola)
			{
				flag=true;
				break;
			}
			
		}
		}
		
		return flag;
	}
	
	
	public String getNomeM(int matricola)
	{
		
			
		for(Studente s:studenti)
		{
			if(s.getMatricola()==matricola)
				return s.getNome();
		}
		
		return null;
	}
	
	public String getCognomeM(int matricola)
	{
		
		
		
		for(Studente s:studenti)
		{
			if(s.getMatricola()==matricola)
				return s.getCognome();
		}
		
		return null;
	}
	
	
	

}
