package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList;

public class Customer extends User {
	
	private static final long serialVersionUID = 8234513441513658859L;
	private FidelityCard fidelityCard ;
	/**
	 * true if the customer wants to be notified whenever a new special offer is set
	 */
	private boolean consensus = false ;
	/**
	 * the board which will show all the new offers if consensus is true
	 */
	private Board offerBoard ;
	
	private Position address ;
	
	private String email ;
	
	private String phoneNumber ;
	/**
	 * enables the customer to register to different fidelity programs
	 */
	private FidelityCardFactory fidelityCardFactory = new FidelityCardFactory() ;
	
	/**
	 * creates a customer who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 */
	public Customer(String name, String surname, String userName, String password) {
		super(name, surname, userName, password);
		this.fidelityCard = new BasicFidelityCard();
		this.offerBoard = new Board<String>() ;
		this.address = null ;
		this.email = "" ;
		this.phoneNumber = "" ;
				
		this.setUserType ("customer") ;
	}
	
	/**
	 * creates a customer who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param address : the address of the user
	 * @param email : the email of the user
	 * @param phoneNumber : the phone number of the user
	 */
	public Customer(String name, String surname, String userName, String password, Position address, String email, String phoneNumber) {
		super(name, surname, userName, password);
		this.fidelityCard = new BasicFidelityCard();
		this.offerBoard = new Board<String>() ;
		this.address = address ;
		this.email = email ;
		this.phoneNumber = phoneNumber ;
		
		this.setUserType ("customer") ;
	}
	
	/**
	 * Adds an offer to the offerBoard
	 * @param offer : the message which describes the offer
	 */
	public void addOffer(String offer){
		if(consensus){
			offerBoard.addObs(offer);
		}
	}
	
	public void setFidelityCard(FidelityCard fidelityCard){
		this.fidelityCard = fidelityCard ;
	}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}
	
	/**
	 * registers the user to a Fidelity Card Plan of a given type : BasicFidelityCard, PointFidelityCard, LotteryFidelityCard or Null to unregister
	 * @param cardType : "basic", "point", "lottery" or "none" to unregister
	 */
	public void registerFidelityCard (String cardType){
		FidelityCard fidelityCard = this.fidelityCardFactory.createFidelityCard(cardType);
		this.fidelityCard = fidelityCard;
	}
	
	/**
	 * displays the informations of the user's fidelity card plan
	 */
	public void displayFidelityInfo(){
		System.out.println(this.fidelityCard.toString());
	}
	
	/**
	 * gets the history of all the orders the customer made on MyFoodora
	 * @param myFoodora : the MyFoodora core
	 * @return historyOfOrders : all the orders the customer made on MyFoodora
	 */
	public ArrayList<Order> getHistoryOfOrders (MyFoodora myFoodora){
		ArrayList<Order> historyOfOrders = new ArrayList<Order>();
		
		ArrayList<Order> completedOrders = myFoodora.getCompletedOrders();
		for(Order order : completedOrders){
			//we seek the completed orders made by the customer
			if (order.getCustomer().getUniqueID() == this.getUniqueID()){
				historyOfOrders.add(order);
			}
		}
		return historyOfOrders;
	}
	
	/**
	 * submits the completed order to myFoodora
	 * @param order : the completed order
	 * @param applyReduction : "true" if the customer wants to apply a reduction using his fidelity card
	 * @param myFoodora : MyFoodora core
	 */
	public void submitOrder (Order order, boolean applyReduction, MyFoodora myFoodora){
		order.submit(applyReduction, myFoodora);
		this.payOrder(order);
	}
	
	/**
	 * the client pays the order (the payment is not implemented in the project)
	 * @param order : the submitted order to pay
	 */
	public void payOrder(Order order){
		System.out.println("You have paid " + order.getPrice() + " euros for your order\n");
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
