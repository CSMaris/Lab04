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

public class StudenteDAO {
	public List<Studente> getTuttiStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");
				studenti.add(new Studente(matricola,cognome,nome,CDS));

				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Corso> getCorsiDiStudente(Studente studente) {//fatto così facci dei duplicati di oggetti studente già esistenti
                                                                 //ma va bene così
   int matricola=studente.getMatricola();
   List<Corso> corsi=new ArrayList<>();


   String sql="SELECT DISTINCT corso.codins, corso.crediti, corso.nome, corso.pd FROM corso inner join iscrizione on corso.codins=iscrizione.codins WHERE iscrizione.matricola=?";
    try {
     Connection conn = ConnectDB.getConnection();

      PreparedStatement st = conn.prepareStatement(sql);
     st.setInt(1, matricola);
      ResultSet rs = st.executeQuery();
      while (rs.next())
    {
     String codice=rs.getString("codins");
     int crediti=rs.getInt("crediti");
     String nome=rs.getString("nome");
     int pd=rs.getInt("pd");
  
    corsi.add(new Corso(nome,codice,crediti,pd));
    }

    }
    catch(SQLException e)
    {
   throw new RuntimeException("Errore Db");	
     }
      return corsi;

    }

     }
