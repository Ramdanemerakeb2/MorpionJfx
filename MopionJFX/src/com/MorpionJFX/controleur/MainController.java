package com.MorpionJFX.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainController implements Initializable {
	
	@FXML 
	private Button nvPartie;
	
	
	
	
	@FXML
	private void lancerPartie(ActionEvent e){
		ControllerVue.exitWindows(nvPartie);
	    Class c = getClass();
	    ControllerVue.openWindows(1,c);
	}
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hello");
		
	}
	
	

}
