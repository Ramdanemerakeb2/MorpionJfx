package com.MorpionJFX.model;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Joueur {
	
	
	String Sym ;
	public static int tour  ;
	Background bg ;
	boolean win = false ;
	int nombre_win = 0 ;
	
	
	
	public Joueur(String sym) {
		
		this.Sym = sym ;
	}
	
	
	
	public void setSym(String sym) {
		this.Sym = sym ;
	}
	
	public String getSym() {
		return this.Sym ;
	}
	
	public void setTour(int i) {
		tour = i ;
	}
	
	public int getTour() {
		return tour ;
	}
	
	//creation des backgroud selon le symbol 
	public void setBackground() {
		if((this.Sym).compareTo("croix") == 0 ) {
			BackgroundImage bgImage = new BackgroundImage( new Image( getClass().getResource("/com/MorpionJFX/ressources/cross.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, true, true, true, false));
	        this.bg = new Background(bgImage);
		}
		if((this.Sym).compareTo("cercle") == 0 ) {
			BackgroundImage bgImage = new BackgroundImage( new Image( getClass().getResource("/com/MorpionJFX/ressources/circle.png").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(50, 50, true, true, true, false));
	        this.bg = new Background(bgImage);
		}
	}
	public Background getBackground() {
		return this.bg ;

	}
	
	public void setWin(Boolean test) {
		this.win=test ;
	}
	
	public void setNombreWin() {
		this.nombre_win = this.nombre_win +1 ;
	}
	public int getNombreWin() {
		return this.nombre_win;
	}
	
	public boolean getWin() {
		return this.win ;
	}
}
