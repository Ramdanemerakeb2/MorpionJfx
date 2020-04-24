package com.MorpionJFX.controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	@FXML 
	private Button nvPartie;
	
	@FXML 
	private Button ldPartie;
	
	
	@FXML
	private void lancerPartie(ActionEvent e){
	    exitWindows();
	    openWindows();
	}
	
	@FXML
	private void loadPartie(ActionEvent e){
	    exitWindows();
	    openWindows();
	}
	
	private void exitWindows() {
		// on recupere la scene courrante puis la fermer
	    Stage stage = (Stage) nvPartie.getScene().getWindow();
	    stage.close();
	}
	
	private void openWindows() {
		Stage primaryStage = new Stage();
	    Parent root = null;
	    try {
	        root = FXMLLoader.load(getClass().getResource("../vue/VueJeu.fxml"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    primaryStage.setTitle("2 em fenetre");
	    primaryStage.setScene(new Scene(root, 600, 400));
	    primaryStage.show();
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hello");
		
	}
	
	

}
