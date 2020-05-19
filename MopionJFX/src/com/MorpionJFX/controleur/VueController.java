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
import javafx.scene.control.TextField;

public class VueController implements Initializable {
	
	@FXML 
	private Button nvPartie;
	
	@FXML 
	private ComboBox<String> ComboBoxChoix;
	
	@FXML 
	private TextField tempsTour ;
	
	
	public String Choix ;
	
	public static String temps ;
	
	
	
	
	//elle permet de lancer une partie selon le choix de l'utilisateur si le champs de temps est remplit  
	@FXML
	private void lancerPartie(ActionEvent e){
		
		//recuperer le temps par tour selectionné par l'utilisateur
		temps = tempsTour.getText();	
		System.out.println(temps);
		
		
		if (temps.equals("")) {
			//on remet le backgroun de tempsTour a rouge a fin de rapeller l'obligation d'indication du temps 
			tempsTour.setStyle("-fx-background-color: red;");
			System.out.println("il faut indiquer le temps d'un tour de jeu ");
		}else {
		
			
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
	    
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//le boutton nvPartie est desactiver  
		nvPartie.setDisable(true); 
		
		//initialiser le combobox avec les deux choix Humain et IA
		ComboBoxChoix.getItems().removeAll(ComboBoxChoix.getItems());
		ComboBoxChoix.getItems().addAll("Humain", "IA");
		
		//recuperer le choix selectionné par l'utilisateur 
		ComboBoxChoix.getSelectionModel().selectedItemProperty()
	    .addListener(new ChangeListener<String>() {
	        public void changed(ObservableValue<? extends String> observable,
	                            String oldValue, String newValue) {
	           System.out.println("Vous allez jouer contre : "+newValue);
	           //activation du boutton nvPartie 
	           if (newValue != "") {
	        	   Choix = newValue ;
	        	   nvPartie.setDisable(false);
	        	   
	           }
	           
	           	  
	           
	        }   
	    });
		
		
		
	}
	
	

}
