package fr.ecp.is1220.MyFoodora;

public class Customer extends User {
	
	private static final long serialVersionUID = 8234513441513658859L;
	private FidelityCard fidelityCard ;
	/**
	 * true if the customer wants to be notified whenever a new special offer is set
	 */
	private boolean consensus = true ;
	/**
	 * the board which will show all the new offers if consensus is true
	 */
	private Board offerBoard ;
	
	private Position address ;
	
	/**
	 * creates a customer who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param surname : the surname of the user
	 * @param address : the address of the user
	 */
	public Customer(String name, String surname, String userName, String password, Position address) {
		super(name, surname, userName, password);
		this.fidelityCard = new BasicFidelityCard();
		this.offerBoard = new Board() ;
		this.address = address ;
		
		this.setUserType ("customer") ;
	}
	
	/**
	 * Adds an offer to the offerBoard
	 * @param offer : the message which describes the offer
	 */
	public void addOffer(String offer){
		if(consensus){
			offerBoard.addOffer(offer);
		}
	}
	
	public void setFidelityCard(FidelityCard fidelityCard){
		this.fidelityCard = fidelityCard ;
	}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	public boolean isConsensus() {
		return consensus;
	}

	public void setConsensus(boolean consensus) {
		this.consensus = consensus;
	}

	public Position getAddress() {
		return address;
	}

	public void setAddress(Position address) {
		this.address = address;
	}
}
