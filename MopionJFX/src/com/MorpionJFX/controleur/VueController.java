package com.MorpionJFX.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class VueController implements Initializable {
	
	@FXML 
	private Button nvPartie;
	
	@FXML 
	private ComboBox<String> ComboBoxChoix;
	
	public String Choix ;
	
	
	
	
	
	@FXML
	private void lancerPartie(ActionEvent e){
		
		System.out.println(Choix);
		MainController.exitWindows(nvPartie);
	    Class c = getClass();
	    switch (Choix) {
		case "IA":
			MainController.openWindows(3,c);
			break;

		case "Humain":
			MainController.openWindows(1,c);
			break;
		}
	    
	    
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hello");
		
		//initialiser le combobox avec les deux choix Humain et IA
		ComboBoxChoix.getItems().removeAll(ComboBoxChoix.getItems());
		ComboBoxChoix.getItems().addAll("Humain", "IA");
		
		//recuperer le choix selectionné par l'utilisateur 
		ComboBoxChoix.getSelectionModel().selectedItemProperty()
	    .addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable,
	                            String oldValue, String newValue) {
	           System.out.println("Vous allez jouer contre : "+newValue);
	           Choix = newValue ;
	           	  
	           
	        }   
	    });
		
		
	}
	
	

}
