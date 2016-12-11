package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList ;

public class Board implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3052025800155606005L;
	/**
	 * The list of the offers which will be shown to the customer
	 */
	private ArrayList<String> offers ; 
	
	public Board() {
		offers = new ArrayList<String>() ;
	}
	
	/**
	 * adds a new offer to the board
	 * @param offer : the offer which is added
	 */
	public void addOffer(String offer){
		offers.add(offer) ;
	}
	
	/**
	 * clears the board
	 */
	public void clearBoard(){
		offers.clear();
	}
	
	/**
	 * shows all the offers of the board
	 * @return a string value of all the offers
	 */
	public String showBoard(){
		String message = "" ;
		for(String offer : offers){
			message += offer + "\n" ;
		}
		return message ;
	}
}
