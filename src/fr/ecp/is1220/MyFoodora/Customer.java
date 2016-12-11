package Group9_Project_IS1220_part1_Colas_Prabakaran;

public class Customer extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8234513441513658859L;
	private String surname ;
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
	public Customer(String name, String userName, String password, MyFoodora myFoodora, String surname, Position address) {
		super(name, userName, password, myFoodora);
		this.surname = surname ;
		this.fidelityCard = new BasicFidelityCard() ;
		this.offerBoard = new Board() ;
		this.address = address ;
		userType = "Customer" ;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}	
}
