
	/**
	 * Sample Skeleton for 'SegreteriaStudenti.fxml' Controller Class
	 */

	package it.polito.tdp.lab04.controller;

	import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
	import javafx.scene.control.TextArea;
	import javafx.scene.control.TextField;
	

	public class SegreteriaStudentiController {
		
		private Model model;

	    @FXML // ResourceBundle that was given to the FXMLLoader
	    private ResourceBundle resources;
	    
	    @FXML // fx:id="btnVrfy"
	    private Button btnVrfy; // Value injected by FXMLLoader

	    @FXML // URL location of the FXML file that was given to the FXMLLoader
	    private URL location;

	    @FXML // fx:id="combo"
	    private ComboBox<Corso> combo; // Value injected by FXMLLoader

	    @FXML // fx:id="searchSbtn"
	    private Button searchSbtn; // Value injected by FXMLLoader

	    @FXML // fx:id="txtM"
	    private TextField txtM; // Value injected by FXMLLoader

	    @FXML // fx:id="txtN"
	    private TextField txtN; // Value injected by FXMLLoader

	    @FXML // fx:id="txtC"
	    private TextField txtC; // Value injected by FXMLLoader

	    @FXML // fx:id="searchCbtn"
	    private Button searchCbtn; // Value injected by FXMLLoader

	    @FXML // fx:id="subBtn"
	    private Button subBtn; // Value injected by FXMLLoader

	    @FXML // fx:id="listArea"
	    private TextArea listArea; // Value injected by FXMLLoader
	    
	    @FXML // fx:id="check"
	    private CheckBox check; // Value injected by FXMLLoader

	    @FXML // fx:id="resetBtn"
	    private Button resetBtn; // Value injected by FXMLLoader
	    
	    @FXML
	    void doCheck(ActionEvent event) {
	    	boolean f=check.isSelected();
	    	if(f) {
	    	try {
	    	int matricola=Integer.parseInt(txtM.getText());
	    	
	    	String nome=model.getNomeM(matricola);
	    	String cognome=model.getCognomeM(matricola);
	    	if(nome!= null && cognome!=null)
	    	{
	    	txtN.setText(nome);
	    	txtC.setText(cognome);
	    	}
	    	else
	    	{
	    		txtN.setText("Matricola non presente");
	    		txtC.setText("Matricola non presente");
	    	}
	    	}
	    	catch(NumberFormatException e)
	    	{
	    	System.out.println("Inserisci una matricola numerica");	
	    	}
	    	
	    	}
	    }
	    
	    @FXML
	    void doVerify(ActionEvent event) {
	    	int matricola=Integer.parseInt(txtM.getText());
	    	String corso=combo.getSelectionModel().getSelectedItem().getCodice();
	    	boolean iscritto=model.iscrittoCorso(matricola, corso);
	    	if(iscritto)
	    		listArea.setText("Studente già iscritto al corso");
	    	else
	    		listArea.setText("Studente non iscritto al corso");	

	    }


	    @FXML
	    void Subscribe(ActionEvent event) {
	    	
	    	try {
		    	int matricola=Integer.parseInt(txtM.getText());
		    	Corso corso=combo.getSelectionModel().getSelectedItem();
	    	
	    	boolean statoIscrizione=model.iscrivi(corso,matricola);
	    	
	    	if(statoIscrizione)
	    		listArea.setText("Iscrizione avvenuta con successo");
	    	else
	    		listArea.setText("Studente già iscritto a quel corso");
	    	
	    	}
	    	
	    	catch(NumberFormatException e)
	    	{
	    	System.out.println("Inserisci una matricola numerica");	
	    	}
	    	

	    }

	    @FXML
	    void doReset(ActionEvent event) {
	    	listArea.clear();
	    	txtN.clear();
	    	txtC.clear();
	    	txtM.clear();

	    }

	    @FXML
	    void searchC(ActionEvent event) {
	    	try {
	    	int matricola=Integer.parseInt(txtM.getText());
	    	List<Corso> listaC=model.getCorsiStudente(matricola);
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("Elenco corsi: \n");
	    	
	    	for(Corso c:listaC)
	    	{
	    		sb.append(c.getNome()+" "+c.getCodice()+"\n");
	    	}
	    	
	    	listArea.setText(sb.toString());

	    	}
	    	catch(NumberFormatException e)
	    	{
	    	System.out.println("Inserisci una matricola numerica");	
	    	}
	    	

	    }

	    @FXML
	    void searchSubs(ActionEvent event) {
	    	
	    	Corso corso=combo.getSelectionModel().getSelectedItem();
	    	if(corso.getCodice().equals("nullo"))
	    		listArea.setText("ERRORE: inserisci un corso");
	    	else {
	    	List<Studente> listaS=model.getIscritti(corso);
	    	StringBuilder sb = new StringBuilder();
	    	sb.append("Elenco studenti: \n");
	    	
	    	for(Studente s:listaS)
	    	{
	    		sb.append(s.getCognome()+" "+s.getNome()+"\n");
	    	}
	    	
	    	listArea.setText(sb.toString());
	    	}

	    }

	    @FXML // This method is called by the FXMLLoader when initialization is complete
	    void initialize() {
	        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert searchSbtn != null : "fx:id=\"searchSbtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtM != null : "fx:id=\"txtM\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert txtC != null : "fx:id=\"txtC\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert searchCbtn != null : "fx:id=\"searchCbtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert subBtn != null : "fx:id=\"subBtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert listArea != null : "fx:id=\"listArea\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert resetBtn != null : "fx:id=\"resetBtn\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert check != null : "fx:id=\"check\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	        assert btnVrfy != null : "fx:id=\"btnVrfy\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

	    }

		public void setModel(Model model) {
			this.model=model;
			List<Corso> corsi=model.listaCorsi();
			corsi.add(new Corso("   ","nullo",0,0));
			this.combo.getItems().addAll(corsi);//aggiunge i nomi perchè ho messo il nome nel toString di corso, ma selezionando 
			                                    //seleziono gli oggetti 
			
				
		}
	}



