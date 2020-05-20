package com.MorpionJFX.model;

import java.util.ArrayList;

public class VerificationPartieJvsJ {
	
    ArrayList<String> gagner = new ArrayList<String>();
	
	public String list[][] = new String [3][3];
	
	
	public void initialize()
	{
		for(int i = 0;i<3;i++)
			
		{
				for(int j=0 ; j<3 ; j++) 
				list[i][j] = "rien";		
		}
	}
	
	//elle permet de verifier si la table du jeu est plein 
	public boolean full()
	{	
		boolean verif = true; 
		for(int i = 0;i<3;i++)
			
		{
			for(int j=0 ; j<3 ; j++) {
				
				if(this.list[i][j].equals("rien")) {
						return false ;
				}
		     }
		}
		return verif ;
	}
	
	//elle indique la machine peut jouer sur la case ou non 
	public boolean verifPos(int i , int j)
	{	
		boolean verif = false; 
		
			if(this.list[i][j].equals("croix") || this.list[i][j].equals("cercle") ) {
						return true ;
				}
		 
		return verif ;
	}
	
	public void affiche()
	{
		for(int i = 0;i<3;i++)
		{
			for(int j=0 ; j<3 ; j++) 
			{
				System.out.print(list[i][j] );	
			}
			System.out.println("");
		}
	}
	
	public void setList(int i , int j , String symb )
	{
		
		this.list[i][j] = symb ;
	}
	
	public String[][] getList()
	{
		
		return this.list;
	}
	
	public void setSym(int i , int j , String symb)
	{
		
		setList(i,j,symb);

	}
	
	//elle permet de verifier si l'un des jouer a gagné 
	public ArrayList<String> rools() {	
			
		for(int i = 0;i<3;i++)
		{   
			//verification des lignes 
			if((list[i][0]).compareTo("cercle") == 0 && (list[i][1]).compareTo("cercle") == 0 && (list[i][2]).compareTo("cercle") == 0 )
			{
				gagner.add("cercle");
				gagner.add(Integer.toString(i));
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(i));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(i));
				gagner.add(Integer.toString(2));
				return gagner;
			}
			//verification des colonnes 
			if((list[i][0]).compareTo("croix") == 0 && (list[i][1]).compareTo("croix") == 0 && (list[i][2]).compareTo("croix") == 0 )
			{
				gagner.add("croix");
				gagner.add(Integer.toString(i));
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(i));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(i));
				gagner.add(Integer.toString(2));
				return gagner;
				
			}
			
		}
		
		for(int j = 0 ; j<3 ; j++)
		{
			
			if((list[0][j]).compareTo("cercle") == 0 && (list[1][j]).compareTo("cercle") == 0 && (list[2][j]).compareTo("cercle") == 0 )
			{
				gagner.add("cercle");
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(j));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(j));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(j));
				return gagner;
				}
			
			if((list[0][j]).compareTo("croix") == 0 && (list[1][j]).compareTo("croix") == 0 && (list[2][j]).compareTo("croix") == 0 )
			{
				gagner.add("croix");
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(j));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(j));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(j));
				return gagner;
				}
			
		}
			//verification des diagonales 
			if((list[0][0]).compareTo("croix") == 0 && (list[1][1]).compareTo("croix") == 0 && (list[2][2]).compareTo("croix") == 0){
				gagner.add("croix");
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(2));
				return gagner;
			
			}
		
			if((list[0][0]).compareTo("cercle") == 0 && (list[1][1]).compareTo("cercle") == 0 && (list[2][2]).compareTo("cercle") == 0)
			{
				gagner.add("cercle");
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(2));
				return gagner;		}
		
			if((list[0][2]).compareTo("croix") == 0 && (list[1][1]).compareTo("croix") == 0 && (list[2][0]).compareTo("croix") == 0)
			{
				gagner.add("croix");
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(0));
				return gagner;		}
		
			if((list[0][2]).compareTo("cercle") == 0 && (list[1][1]).compareTo("cercle") == 0 && (list[2][0]).compareTo("cercle") == 0)
			{
				gagner.add("cercle");
				gagner.add(Integer.toString(0));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(1));
				gagner.add(Integer.toString(2));
				gagner.add(Integer.toString(0));
				return gagner;	
				}
			
		return null;
	
	}

}
