package com.MorpionJFX.controleur;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.MorpionJFX.model.Joueur;
import com.MorpionJFX.model.VerificationPartieJvsJ;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class VueIAController implements Initializable {

	@FXML
    private ImageView ImgJoueur1 , ImgJoueur2,ImgJoueur11,ImgJoueur22,timer;

	@FXML
    private Button buttonMenu , buttonReplay ;
	
	@FXML
    public Label labelJoueur1 ,labelJoueur2, labelGagnant,scoreJoueur1,scoreJoueur2 ;
	
	@FXML
	public Label tempsJeu;
	
	@FXML
	private GridPane tableJeu ;
	
	@FXML
    private Pane paneVueJeu; //la pane courante qui contient l'activité a afficher 
	
	@FXML
    private HBox HboxPane;
	
	
	
	int depart = 0 ;
	static public int nombre_win1 ;
	static public int nombre_win2 ;
	public String tempsTour ; 
	public boolean finJeu ;
	
	// generator de position (i , j) pour la machine 
	public Random generPos;
	
	
	public Joueur joueur1 = new Joueur("croix") ;
	public Joueur IA = new Joueur("cercle");
	
	
	
	static VerificationPartieJvsJ partie1 = new VerificationPartieJvsJ();
	
	//ArrayList<String> win = new ArrayList<String>();
	
	
	//On charge les images du package ressources et on les insere dans initialize
    InputStream input1 = this.getClass().getResourceAsStream("/com/MorpionJFX/ressources/cross.png");
    InputStream input2 = this.getClass().getResourceAsStream("/com/MorpionJFX/ressources/circle.png");
    InputStream input3 = this.getClass().getResourceAsStream("/com/MorpionJFX/ressources/timer.png");

    Image image1 = new Image(input1);
    Image image2 = new Image(input2);
    Image image3 = new Image(input3);
    
    
    //cette fonction permet de mettre a jour le label de temps chaque seconde
    private void update() {
    	Timer timer = new Timer();
    	timer.schedule(new TimerTask() { // timer task to update the seconds
    	    @Override
    	    public void run() {
    	        
    	        Platform.runLater(new Runnable() { 
    	            public void run() {
    	            	
    	            	int t = Integer.parseInt(tempsJeu.getText()) ;
    	        		int t1 = t-1 ;
    	        		tempsJeu.setText(Integer.toString(t1));
    	        		
    	        		//le cas ou le temps est ecoulé on change de tour 
    	        		if (t1 == -1){
    	        			
    	        			if( IA.getTour()) {
    	        				joueur1.setTour(true);
    	        				IA.setTour(false);
    	        				labelJoueur2.setTextFill(Color.BLACK);
    	    					labelJoueur1.setTextFill(Color.GREEN);
    	    					tempsJeu.setText(tempsTour);
    	    					 
    	        			}else {
    	        				if( joueur1.getTour()) {
	    	        				joueur1.setTour(false);
	    	        				IA.setTour(true);
	    	        				labelJoueur1.setTextFill(Color.BLACK);
	    							labelJoueur2.setTextFill(Color.GREEN);
	    							
	    							tempsJeu.setText(tempsTour);
    	        			        }
    	        				}
    	        			
    	        			}
    	        		//le cas de victoir on arrete le timer 
    	        		if(joueur1.getNombreWin() != 0 || IA.getNombreWin() != 0 ) {timer.cancel();}
    	        		//le cas ou la table est remplie est que personne n'a gagné on arrete le timer
    	        		if(finJeu) {timer.cancel();}
    	        		
    	        		
    	}});}}, 1000, 1000); //Every 1 second
    }
    
    
    
    
    //elle retourne un node selon sa position dans tableJeu 
    private Node getNodeFromGridPane(  int row ,int col, GridPane gridPane) {
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node).intValue() == row
                    && GridPane.getColumnIndex(node).intValue() == col)
                return node;
        return null;
    }
    
  
    
   
    
    
	
    //les deux fonctions font appele aux fonctions de ControllerVue 
    //cette fonction permet de fermer la fenetre VueJeu et ouvre la fentre de Vue 
    @FXML
	private void backMenu(ActionEvent e){
    	MainController.exitWindows(buttonMenu);
	    Class c = getClass();
	    MainController.openWindows(2,c);
	}
    
    
  //cette fonction permet de relance la vue VueJeu avec une transition
    @FXML
	private void replayGame(ActionEvent e){
    	try {
    		//on relance la meme fenetre avec une transition 
            Parent Root = FXMLLoader.load(getClass().getResource("../vue/VueIA.fxml"));
            Scene Scene = buttonReplay.getScene();
            Root.translateYProperty().set(Scene.getHeight());
            
            paneVueJeu.getChildren().add(Root);
            
            //creation une ligne temporelle
            Timeline tl = new Timeline();
            
            //specifier le type d'interpolation a utiliser dans  l_oTl
            KeyValue kv = new KeyValue(Root.translateYProperty(), 0, Interpolator.EASE_OUT);
            
            //creation de l'animation
            KeyFrame kf = new KeyFrame(Duration.millis(1000), kv);
            
            //on ajoute l'animation a la ligne temporelle
            tl.getKeyFrames().add(kf);
            
            // pour supprimer la scene precedente 
            tl.setOnFinished(event -> {
            	
            	paneVueJeu.getChildren().remove(HboxPane);
            });
            
            //jouer l'animation de transition entre les deux vues 
            tl.play();
        } catch (IOException E){
            System.out.println("[ERROR] ");
        }
	}
    
    //cette fonction permet de creer deux annimation qui se lance parallelement (l’opacité et la dimension) 
    public void annimationLabelGagnant(Label l)
	{   
    	//transition d’opacité sur 1 seconde
	    FadeTransition ft = new FadeTransition(Duration.millis(1000),l);
	    ft.setFromValue(0.1);
	    ft.setToValue(0.3);
	    ft.setCycleCount(40);
	    ft.setAutoReverse(true);
	    //ft.play();
	    
	    //transition de dimension sur 1 seconde
	    ScaleTransition st = new ScaleTransition(Duration.millis(1000), l);
	    st.setByX(0.2f);
	    st.setByY(0.2f);
	    st.setCycleCount(40);	
	    st.setAutoReverse(true);
	    //st.play();
	    
	    //lancer la transition de dimension et d'opacité parallelement  
	    ParallelTransition parallelTransition = new ParallelTransition(); 
	    parallelTransition.getChildren().addAll(ft, st);
	    parallelTransition.setCycleCount(Timeline.INDEFINITE) ;
	    parallelTransition.play();
	    
	    
	}
    
  //cette fonction permet de creer l'annimation de retation  
    public void annimationVictoire(Node node)
	{
    
	    RotateTransition rt = new RotateTransition(Duration. millis(3000), node);
	    rt.setByAngle(360);
	    rt.setCycleCount(40);
	    rt.setAutoReverse(true);
	    rt.play();
	    
	    //finJeu = true ;
    
	}
    
  
    @FXML
	private void jouer(ActionEvent e) throws Exception{
    	if(depart == 0){
    		
			//creation d'une matrice qui represente notre tableJeu
    		partie1.initialize();
    		
			joueur1.setWin(false);
			IA.setWin(false);
			
			depart = 1 ;
			
			generPos = new Random();
		}
	
    	
	if(joueur1.getWin() == false) 
	{
		for(int tour = 0 ; tour<9 ; tour++)
		{
			if( joueur1.getTour())
			{	
				
				Button btnclicked =(Button) e.getSource();
				
				if(btnclicked.getId()== null) {
					
					//on affiche la croix sur la tableJeu 
					btnclicked.setId(joueur1.getSym());
					joueur1.setBackground();
					btnclicked.setBackground(joueur1.getBackground());
					
					//on retrouve la position du boutton dans tableJeu 
					Integer i = GridPane.getRowIndex(btnclicked);
					Integer j = GridPane.getColumnIndex(btnclicked);
				
					System.out.println("i(1) ==="+i);
					System.out.println("j(2) ==="+j);
					
					//on insere un symb dans la matrice selon la position du boutton 
					partie1.setSym(i,j,"croix");
	
					if(partie1.rools() != null) {
						
					
						
						if(partie1.rools().get(0) == "cercle") 
							{
								if((joueur1.getSym()).compareTo("cercle") == 0)
								{
										joueur1.setWin(true);
										
										break ;
								}
							}
			
							if(partie1.rools().get(0) == "croix") 
							{
								if((joueur1.getSym()).compareTo("croix") == 0)
								{
									joueur1.setWin(true);
									
		
									break ;
									
								}
							}
				   }
	
					if(joueur1.getWin() == false)
					{   
						//on change de tour 
						IA.setTour(true);
						joueur1.setTour(false);
						
						//on met a jour les couleurs des label selon le tour 
						labelJoueur1.setTextFill(Color.BLACK);
						labelJoueur2.setTextFill(Color.GREEN);
						
						//reinitialisation du temps 
						tempsJeu.setText(tempsTour);
						
					}
					
			       break;
	
			}
			}
	
			
		}
		
		if(joueur1.getWin() == true || IA.getWin() == true)
		{
			System.out.println("victoir");
			
			//le cas de victoir on applique une retation sur les boutton de la ligne ou la collone ou bien la diagonale 
			annimationVictoire(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(1)),Integer.parseInt(partie1.rools().get(2)),tableJeu));
			annimationVictoire(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(3)),Integer.parseInt(partie1.rools().get(4)),tableJeu));
			annimationVictoire(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(5)),Integer.parseInt(partie1.rools().get(6)),tableJeu));
			
			
			if(joueur1.getWin() == true)
			{   
				//lancer la transition de dimension et d'opacité
				labelGagnant.setText("Le gagnant est le Joueur 1");
				annimationLabelGagnant(labelGagnant);
				
	
				//incrementation de score pour le joueur 1
				joueur1.setNombreWin();
				nombre_win1++ ;
				
				//mise a jour du score sur l'interface 
				scoreJoueur1.setText(Integer.toString(nombre_win1));
			}
			if(IA.getWin() == true) 
			{
				
				
				//lancer la transition de dimension et d'opacité
				labelGagnant.setText("Le gagnant est le Joueur 2");
				annimationLabelGagnant(labelGagnant);
				
				//incrementation de score pour le joueur 2
				IA.setNombreWin();
				nombre_win2 ++ ;
				
				//mise a jour du score sur l'interface 
				scoreJoueur2.setText(Integer.toString(nombre_win2));
			}
			
			partie1.rools().clear();
			}
		
	}
	//la tableJeu est remplie et personne n'a gagné 
	 if(partie1.full() && joueur1.getWin() == false && IA.getWin() == false) {
		finJeu = true ;
		labelGagnant.setText("Aucun joueur n'a gagné");
		annimationLabelGagnant(labelGagnant);
	 }
	}
    
    
    public void jouerIA() {
    	Timer timer2 = new Timer();
    	timer2.schedule(new TimerTask() { // timer task to update the seconds
    	    @Override
    	    public void run() {
    	        
    	        Platform.runLater(new Runnable() { 
    	            public void run() {
    	               if(IA.getWin() == false){	
    	            	for(int tour = 0 ; tour<9 ; tour++){
    	            	
    	            	  if(IA.getTour())
    	    			    {    
    	    				
    	    				int posI;
    	    				int posJ;
    	    				// Generer un deplacement aléatoire 
    	    				do
    	    				{
    	    					posI = generPos.nextInt(2);
    	    					posJ = generPos.nextInt(2);
    	    				} while (partie1.verifPos(posI, posJ));
    	    				
    	    				Button btnclicked = (Button) getNodeFromGridPane(posI, posJ, tableJeu) ;
    	    				
    	    				//le meme traitement que le joueur 1
    	    				//Button btnclicked = (Button)e.getSource();
    	    				
    	    				if(btnclicked.getId()==null) {
    	    					
    	    					btnclicked.setId(IA.getSym());
    	    					IA.setBackground();
    	    					btnclicked.setBackground(IA.getBackground());
    	    					
    	    					Integer i = GridPane.getRowIndex(btnclicked);
    	    					Integer j = GridPane.getColumnIndex(btnclicked);
    	    					
    	    					System.out.println("i =="+i);
    	    					System.out.println("i =="+j);
    	    					
    	    					partie1.setSym(i,j,"cercle");
    	    		
    	    					if(partie1.rools() != null) {
    	    						
    	    						
    	    						
    	    						if(partie1.rools().get(0) == "cercle") 
    	    						{
    	    								if((IA.getSym()).compareTo("cercle") == 0)
    	    								{
    	    										IA.setWin(true);
    	    										
    	    										//break ;
    	    								}
    	    						}
    	    		
    	    						if(partie1.rools().get(0) == "croix") 
    	    						{
    	    							if((IA.getSym()).compareTo("croix") == 0)
    	    							{
    	    								IA.setWin(true);
    	    								
    	    								//break ;
    	    								
    	    							}
    	    						}
    	    					}
    	    		
    	    					if(IA.getWin() == false) 
    	    					{
    	    						joueur1.setTour(true);
    	    						IA.setTour(false);
    	    						
    	    						labelJoueur2.setTextFill(Color.BLACK);
    	    						labelJoueur1.setTextFill(Color.GREEN);
    	    						
    	    						tempsJeu.setText(tempsTour);
    	    						
    	    						//break;
    	    					}
    	    			    }
    	    		     } 
    	            	}
    	            	
    	            	if(joueur1.getWin() == true || IA.getWin() == true)
    	        		{
    	        			System.out.println("victoir");
    	        			
    	        			//le cas de victoir on applique une retation sur les boutton de la ligne ou la collone ou bien la diagonale 
    	        			annimationVictoire(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(1)),Integer.parseInt(partie1.rools().get(2)),tableJeu));
    	        			annimationVictoire(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(3)),Integer.parseInt(partie1.rools().get(4)),tableJeu));
    	        			annimationVictoire(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(5)),Integer.parseInt(partie1.rools().get(6)),tableJeu));
    	        			
    	        			
    	        			if(joueur1.getWin() == true)
    	        			{   
    	        				//lancer la transition de dimension et d'opacité
    	        				labelGagnant.setText("Le gagnant est le Joueur 1");
    	        				annimationLabelGagnant(labelGagnant);
    	        				
    	        	
    	        				//incrementation de score pour le joueur 1
    	        				joueur1.setNombreWin();
    	        				nombre_win1++ ;
    	        				
    	        				//mise a jour du score sur l'interface 
    	        				scoreJoueur1.setText(Integer.toString(nombre_win1));
    	        			}
    	        			if(IA.getWin() == true) 
    	        			{
    	        				
    	        				
    	        				//lancer la transition de dimension et d'opacité
    	        				labelGagnant.setText("Le gagnant est le Joueur 2");
    	        				annimationLabelGagnant(labelGagnant);
    	        				
    	        				//incrementation de score pour le joueur 2
    	        				IA.setNombreWin();
    	        				nombre_win2 ++ ;
    	        				
    	        				//mise a jour du score sur l'interface 
    	        				scoreJoueur2.setText(Integer.toString(nombre_win2));
    	        			}
    	        			
    	        			partie1.rools().clear();
    	        			}
    	               }	
    	            	
    	            	
    	            	
    	        		//le cas de victoir on arrete le timer 
    	        		if(joueur1.getNombreWin() != 0 || IA.getNombreWin() != 0 ) {timer2.cancel();}
    	        		//le cas ou la table est remplie est que personne n'a gagné on arrete le timer
    	        		if(finJeu) {timer2.cancel();}
    	        		
    	        		
    	}});}}, 1000, 1000); //Every 1 second
    	
    }
   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 
		tempsTour = VueController.temps;
		
		//tempsJeu.setText("10");
		
		//on initialise le temps par tour sur l'interface recuperer du menu principale
		tempsJeu.setText(tempsTour);
		
		//avant de lancer cette fenetre  on insere les images
		ImgJoueur1.setImage(image1);
		ImgJoueur11.setImage(image1);
		ImgJoueur2.setImage(image2);
		ImgJoueur22.setImage(image2);
		timer.setImage(image3);
		
		//on initialise le score sur l'interface 
		scoreJoueur1.setText(Integer.toString(nombre_win1));
		scoreJoueur2.setText(Integer.toString(nombre_win2));
		
		//on fixe que c'est au joueur 1 de commencer le jeu 
		joueur1.setTour(true);
		IA.setTour(false);
		labelJoueur1.setTextFill(Color.GREEN);
		
		
		//en lance notre fonction qui met ajour le tempsJeu  
		update();
		
		jouerIA() ;
		
		
		
	}
	

}
