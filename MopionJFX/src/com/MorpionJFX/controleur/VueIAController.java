package com.MorpionJFX.controleur;



import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VueIAController implements Initializable {

	@FXML
    private ImageView ImgJoueur1;
	@FXML
    private ImageView ImgJoueur2;
	@FXML
    private ImageView ImgJoueur11;
	@FXML
    private ImageView ImgJoueur22;
	@FXML
    private ImageView timer;
	@FXML
    private Button buttonMenu ;
	@FXML
    private Button buttonReplay ;
	
	Class<?> clazz = this.getClass();
	
	//On charge les images du package ressources et on les insere dans initialize
    InputStream input1 = clazz.getResourceAsStream("/com/MorpionJFX/ressources/cross.png");
    InputStream input2 = clazz.getResourceAsStream("/com/MorpionJFX/ressources/circle.png");
    InputStream input3 = clazz.getResourceAsStream("/com/MorpionJFX/ressources/timer.png");

    

    Image image1 = new Image(input1);
    Image image2 = new Image(input2);
    Image image3 = new Image(input3);
	
    //les deux fonctions font appele aux fonctions de ControllerVue 
    //cette fonction permet de fermer la fenetre VueJeu et ouvre la fentre de Vue 
    @FXML
	private void backMenu(ActionEvent e){
    	MainController.exitWindows(buttonMenu);
	    Class c = getClass();
	    MainController.openWindows(2,c);
	}
  //cette fonction permet de fermer la fenetre VueJeu et ouvre la fentre de Vue
    @FXML
	private void replayGame(ActionEvent e){
    	MainController.exitWindows(buttonReplay);
	    Class c = getClass();
	    MainController.openWindows(2,c);
	}
   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//avant de lancer cette fenetre  on inistialise les images 
		ImgJoueur1.setImage(image1);
		ImgJoueur11.setImage(image1);
		ImgJoueur2.setImage(image2);
		ImgJoueur22.setImage(image2);
		timer.setImage(image3);
		System.out.println("hello2");
	}
	

}
