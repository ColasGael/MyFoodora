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
	 * @param obs : the offer which is added
	 */
	public void addObs(ObsType obs){
		this.obs.add(obs) ;
	}
	
	/**
	 * removes an offer from the board
	 * @param obs : the offer which is removed
	 */
	public void removeObs(ObsType obs){
		this.obs.remove(obs) ;
	}
	
	/**
	 * finds the object of ID uniqueID
	 * @param uniqueID : the ID of the object
	 * @return object : the object of ID uniqueID
	 * @throws OrderNotFoundException : if the order is not on the board
	 */
	public ObsType findObsById (int uniqueID) throws OrderNotFoundException {
		
		for (ObsType object : obs){
			if((object instanceof Order)&&(((Order)object).getUniqueID()==uniqueID));
				return object;
		}
		throw(new OrderNotFoundException("The order of ID " + uniqueID + "is not on the board"));
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
	@Override
	public String toString(){
		String message = "Board :\n" ;
		for(ObsType obs : this.obs){
			message += obs + "\n" ;
		}
		return message ;
	}
}
