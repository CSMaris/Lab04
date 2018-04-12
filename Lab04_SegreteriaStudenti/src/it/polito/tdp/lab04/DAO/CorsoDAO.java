package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	
	
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				corsi.add(new Corso(nome,codins,numeroCrediti,periodoDidattico));

				

				
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		//INUTILE, servirebbe per aggiungere info degli oggetti corso tramite il model, prendendo le info diretamente dal DB
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {//fatto così faccio dei duplicati di oggetti studente già esistenti
		                                                          //ma va bene così
		String codiceCorso=corso.getCodice();
		 List<Studente> studenti=new ArrayList<>();
		
		
	String sql="SELECT DISTINCT studente.matricola, studente.cognome, studente.nome, studente.CDS \n" + 
			"FROM studente inner join iscrizione on studente.matricola=iscrizione.matricola\n" + 
			"WHERE iscrizione.codins=?";
	try {
		Connection conn = ConnectDB.getConnection();
		
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, codiceCorso);
		ResultSet rs = st.executeQuery();
		while (rs.next())
		{
		int matricola=rs.getInt("matricola");
		String cognome=rs.getString("cognome");
		String nome=rs.getString("nome");
		String CDS=rs.getString("CDS");
		studenti.add(new Studente(matricola,cognome,nome,CDS));
		}
		
	}
	catch(SQLException e)
	{
		throw new RuntimeException("Errore Db");	
	}
		return studenti;
	
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) {
		
		int matricola=studente.getMatricola();
		String codice=corso.getCodice();
		boolean flag=true;
		
		String sql1="SELECT *\n" + 
				"FROM iscrizione\n" + 
				"where matricola=? AND codins=?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql1);
			st.setInt(1,matricola);
			st.setString(2, codice);
			
			ResultSet rs=st.executeQuery();
			
			if(rs.next())
			flag=false;
			
			}
			
		
		catch(SQLException e)
		{
			throw new RuntimeException("Errore Db");	
		}
		
		if(flag)
		{
			String sql2="INSERT INTO iscrizione VALUES (?,?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql2);
			st.setInt(1, matricola);
			st.setString(2,codice);
			
			st.executeUpdate();
			
			}
			
		
		catch(SQLException e)
		{
			throw new RuntimeException("Errore Db");	
		}
		}
		
		// ritorna true se l'iscrizione e' avvenuta con successo
		return flag;
	}
}

