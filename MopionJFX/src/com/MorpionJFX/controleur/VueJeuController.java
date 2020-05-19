package com.MorpionJFX.controleur;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class VueJeuController implements Initializable {

	@FXML
    private ImageView ImgJoueur1 , ImgJoueur2,ImgJoueur11,ImgJoueur22,timer;

	@FXML
    private Button buttonMenu , buttonReplay ;
	
	@FXML
    private Label labelJoueur1 ,labelJoueur2, labelGagnant,scoreJoueur1,scoreJoueur2 ,tempsJeu;
	
	@FXML
	private GridPane tableJeu ;
	
	@FXML
    private Pane paneVueJeu; //la pane courante qui contient l'activité a afficher 
	
	@FXML
    private HBox HboxPane;
	
	/*@FXML
	private Object btn = new Button();*/
	
	int depart = 0 ;
	int nombre_win ;
	
	static Joueur joueur1 = new Joueur("croix") ;
	static Joueur joueur2 = new Joueur("cercle");
	
	static VerificationPartieJvsJ partie1 = new VerificationPartieJvsJ();
	
	ArrayList<String> win = new ArrayList<String>();
	//Class<?> clazz = this.getClass();
	
	//On charge les images du package ressources et on les insere dans initialize
    InputStream input1 = this.getClass().getResourceAsStream("/com/MorpionJFX/ressources/cross.png");
    InputStream input2 = this.getClass().getResourceAsStream("/com/MorpionJFX/ressources/circle.png");
    InputStream input3 = this.getClass().getResourceAsStream("/com/MorpionJFX/ressources/timer.png");

    Image image1 = new Image(input1);
    Image image2 = new Image(input2);
    Image image3 = new Image(input3);
    
    
    private Node getNodeFromGridPane(  int col,int row, GridPane gridPane) {
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node).intValue() == row
                    && GridPane.getColumnIndex(node).intValue() == col)
                return node;
        return null;
    }
    
    /*public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }*/
    
    
	
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
    	try {
    		//on relance la meme fenetre avec une transition 
            Parent Root = FXMLLoader.load(getClass().getResource("../vue/VueJeu.fxml"));
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
            	//paneVueJeu.getChildren().clear();
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
    
    public void annimation_btn2(Node node)
	{
    
    RotateTransition rt = new RotateTransition(Duration. millis(3000), node);
    rt.setByAngle(360);
    rt.setCycleCount(40);
    rt.setAutoReverse(true);
    rt.play();
    /*FadeTransition ft = new FadeTransition(Duration.millis(1000),node);
    ft.setFromValue(1.0);
    ft.setToValue(0.3);
    ft.setCycleCount(40);
    ft.setAutoReverse(true);
    ft.play();*/
	}
    
  //cette fonction permet de fermer la fenetre VueJeu et ouvre la fentre de Vue
    @FXML
	private void jouer(ActionEvent e) throws Exception{
    	if(depart == 0)
		{
		partie1.initialize();
		joueur1.setWin(false);
		joueur2.setWin(false);
		depart = 1 ;
		
		
		}
	
	if(joueur1.getWin() == false && joueur2.getWin() == false ) 
	{
		for(int tour = 0 ; tour<9 ; tour++)
		{
			if( joueur1.getTour()== 0 )
			{	
				Node source = (Node)e.getSource() ;
		        Integer i = GridPane.getColumnIndex(source);
		        Integer j = GridPane.getRowIndex(source);
		        
		        System.out.println("i(1) ==="+i);
				System.out.println("j(2) ==="+j);
		        
				Button btnclicked =(Button) e.getSource();
				if(btnclicked.getId()== null) {
				btnclicked.setId(joueur1.getSym());
				joueur1.setBackground();
				btnclicked.setBackground(joueur1.getBackground());
				//annimation_btn(btn);
				//Integer i = GridPane.getRowIndex(btnclicked);
				//Integer j = GridPane.getColumnIndex(btnclicked);
				
				partie1.setSym(i,j,"croix");
	
				if(partie1.rools() != null) {
					if(partie1.rools().get(0) == "cercle") 
					{
							if((joueur1.getSym()).compareTo("cercle") == 0)
							{
									joueur1.setWin(true);
									win = partie1.rools();
									break ;
							}
					}
	
					if(partie1.rools().get(0) == "croix") 
					{
						if((joueur1.getSym()).compareTo("croix") == 0)
						{
							joueur1.setWin(true);
							win = partie1.rools();

							break ;
							
						}
					}
				}
	
					if(joueur1.getWin() == false)
					{
						joueur2.setTour(1);
						joueur1.setTour(1);
						//name.setText(joueur2.getNom());
						labelJoueur1.setTextFill(Color.BLACK);
						labelJoueur2.setTextFill(Color.GREEN);
						/*annimation_Label2(name);
						annimation_Label1(name);
						annimation_Label2(tourDe);
						annimation_Label1(tourDe);*/
					}
			break;
	
			}
			}
	
			if(joueur2.getTour() == 1)
			{
				
				Node source = (Node)e.getSource() ;
		        Integer i = GridPane.getColumnIndex(source);
		        Integer j = GridPane.getRowIndex(source);
		        
		        System.out.println("i =="+i);
				System.out.println("i =="+j);
				
				
				Button btnclicked = (Button)e.getSource();
				if(btnclicked.getId()==null) {
				btnclicked.setId(joueur2.getSym());
				joueur2.setBackground();
				btnclicked.setBackground(joueur2.getBackground());
				//annimation_btn(btn);
				//Integer i = GridPane.getRowIndex(btnclicked);
				//Integer j = GridPane.getColumnIndex(btnclicked);
				
				partie1.setSym(i,j,"cercle");
	
				if(partie1.rools() != null) {
				if(partie1.rools().get(0) == "cercle") 
				{
						if((joueur2.getSym()).compareTo("cercle") == 0)
						{
								joueur2.setWin(true);
								win = partie1.rools();
								break ;
						}
				}

				if(partie1.rools().get(0) == "croix") 
				{
					if((joueur2.getSym()).compareTo("croix") == 0)
					{
						joueur2.setWin(true);
						win = partie1.rools();
						break ;
						
					}
				}
				}
	
				if(joueur2.getWin() == false) 
				{
					joueur1.setTour(0);
					joueur2.setTour(0);
					
					labelJoueur2.setTextFill(Color.BLACK);
					labelJoueur1.setTextFill(Color.GREEN);
					/*name.setText(joueur1.getNom());
					annimation_Label2(name);
					annimation_Label1(name);
					annimation_Label2(tourDe);
					annimation_Label1(tourDe);*/
					break;
				}
			}
		}
		}
		
		if(joueur1.getWin() == true || joueur2.getWin() == true)
		{
			System.out.println("tu peux pas");
			//labelGagnant.setText("le gagnant c'est");
			//annimation_Label1(tourDe);
			//annimation_Label2(tourDe);
			joueur1.setTour(0);
			joueur2.setTour(0);
			
			System.out.println(partie1.rools().get(1));
			System.out.println(partie1.rools().get(2));
			System.out.println("***********************************");
			System.out.println(partie1.rools().get(3));
			System.out.println(partie1.rools().get(4));
			System.out.println("***********************************");
			System.out.println(partie1.rools().get(5));
			System.out.println(partie1.rools().get(6));
			
			
			annimation_btn2(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(1)),Integer.parseInt(partie1.rools().get(2)),tableJeu));
			annimation_btn2(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(3)),Integer.parseInt(partie1.rools().get(4)),tableJeu));
			annimation_btn2(getNodeFromGridPane(Integer.parseInt(partie1.rools().get(5)),Integer.parseInt(partie1.rools().get(6)),tableJeu));
			
			if(joueur1.getWin() == true)
			{   
				//lancer la transition de dimension et d'opacité
				labelGagnant.setText("le gagnant c'est Joueur 1");
				annimationLabelGagnant(labelGagnant);
				
				/*name.setText(joueur1.getNom());					
				annimation_Label2(name);
				annimation_Label1(name);*/
				
				//incrementation de score pour le joueur 2
				joueur1.setNombreWin();
				scoreJoueur1.setText(Integer.toString(joueur1.getNombreWin()));
			}
			if(joueur2.getWin() == true) 
			{
				/*name.setText(joueur2.getNom());
				annimation_Label2(name);
				annimation_Label1(name);*/
				
				//lancer la transition de dimension et d'opacité
				labelGagnant.setText("le gagnant c'est Joueur 2");
				annimationLabelGagnant(labelGagnant);
				
				//incrementation de score pour le joueur 2
				joueur2.setNombreWin();
				scoreJoueur2.setText(Integer.toString(joueur2.getNombreWin()));
			}
			partie1.rools().clear();
			}
	}
	}
   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//avant de lancer cette fenetre  on inistialise les images 
		
		ImgJoueur1.setImage(image1);
		ImgJoueur11.setImage(image1);
		ImgJoueur2.setImage(image2);
		ImgJoueur22.setImage(image2);
		timer.setImage(image3);
		scoreJoueur1.setText(Integer.toString(joueur1.getNombreWin()));
		scoreJoueur2.setText(Integer.toString(joueur2.getNombreWin()));
		labelJoueur1.setTextFill(Color.GREEN);
		joueur1.setTour(0);
		joueur2.setTour(0);
		System.out.println("hello2");
	}
	

}
