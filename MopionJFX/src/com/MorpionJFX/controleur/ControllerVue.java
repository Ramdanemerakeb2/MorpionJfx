package com.MorpionJFX.controleur;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerVue {
	
	static void exitWindows(Button b) {
		// on recupere la scene courrante puis la fermer
	    Stage stage = (Stage) b.getScene().getWindow();
	    stage.close();
	}
	
	static void openWindows(int n,Class c) {
		Stage primaryStage = new Stage();
	    Parent root = null;
	    switch (n) {
		case 1:
			try {
		        root = FXMLLoader.load(c.getResource("../vue/VueJeu.fxml"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    primaryStage.setTitle("2 em fenetre");
		    primaryStage.setScene(new Scene(root));
		    primaryStage.setResizable(false);
		    primaryStage.show();
			break;

		case 2:
			try {
		        root = FXMLLoader.load(c.getResource("../vue/Vue.fxml"));
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    primaryStage.setTitle("Morpion Champion");
		    primaryStage.setScene(new Scene(root));
		    primaryStage.setResizable(false);
		    primaryStage.show();
			break;
		}
	    
	}
}
