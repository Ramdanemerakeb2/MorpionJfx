package com.MorpionJFX.controleur;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//On a creer cette classe a fin d'eviter l'ecriture du meme code dans les controlleurs 
public class MainController {
	
	static void exitWindows(Button b) {
		// on recupere la scene courrante puis la fermer
	    Stage stage = (Stage) b.getScene().getWindow();
	    stage.close();
	}
	
	//elle permet d'ouvrrir une nouvelle vue selon l'argument .
	static void openWindows(int n,Class c) {
		Stage primaryStage = new Stage();
	    Parent root = null;
	    switch (n) {
	    
	    //le cas de partie humain vs humain 
		case 1:
			try {
		        root = FXMLLoader.load(c.getResource("../vue/VueJeu.fxml"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    primaryStage.setTitle("Morpion Champion (H vs H)");
		    primaryStage.setScene(new Scene(root));
		    primaryStage.setResizable(false);
		    primaryStage.show();
			break;
			
		//le cas du menu principale  	
		case 2:
			try {
		        root = FXMLLoader.load(c.getResource("../vue/Vue.fxml"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    primaryStage.setTitle("Morpion Champion (Menu Principal)");
		    primaryStage.setScene(new Scene(root));
		    primaryStage.setResizable(false);
		    primaryStage.show();
			break;
		
		//le cas de partie humain vs IA
		case 3:
			try {
		        root = FXMLLoader.load(c.getResource("../vue/VueIA.fxml"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    primaryStage.setTitle("Morpion Champion (H vs IA)");
		    primaryStage.setScene(new Scene(root));
		    primaryStage.setResizable(false);
		    primaryStage.show();
			break;
		}
	    
	}
}
