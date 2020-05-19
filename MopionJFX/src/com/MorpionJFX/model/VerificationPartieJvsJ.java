package com.MorpionJFX.model;

import java.util.ArrayList;

public class VerificationPartieJvsJ {
	
    ArrayList<String> gagner = new ArrayList<String>();
	
	String list[][] = new String [3][3];
	
	
	public void initialize()
	{
		for(int i = 0;i<3;i++)
			
		{
				for(int j=0 ; j<3 ; j++) 
				list[i][j] = "rien";		
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
	
	
	public ArrayList<String> rools() {	
			
		for(int i = 0;i<3;i++)
		{
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
