package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList ;

public class Board <ObsType> implements java.io.Serializable{

	private static final long serialVersionUID = -3052025800155606005L;
	/**
	 * The list of the offers which will be shown to the customer
	 */
	private ArrayList<ObsType> obs ; 
	
	public Board() {
		obs = new ArrayList<ObsType>() ;
	}
	
	/**
	 * adds a new offer to the board
	 * @param offer : the offer which is added
	 */
	public void addObs(ObsType obs){
		this.obs.add(obs) ;
	}
	
	/**
	 * clears the board
	 */
	public void clearBoard(){
		this.obs.clear();
	}
	
	/**
	 * shows all the offers of the board
	 * @return a string value of all the offers
	 */
	public String showBoard(){
		String message = "" ;
		for(ObsType obs : this.obs){
			message += obs + "\n" ;
		}
		return message ;
	}
}
