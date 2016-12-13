package fr.ecp.is1220.MyFoodora;

public class Courier extends User {
	
	private static final long serialVersionUID = 5347546696284207206L;
	private Position position ;
	private String phoneNumber ;
	/**
	 * the number of orders delivered
	 */
	private int counter = 0 ;
	/**
	 * true when the courier is on-duty and false if he is off-duty
	 */
	private boolean onDuty = false ;
	/**
	 * show the delivery calls
	 */
	private Board<Order> board;

	/**
	 * creates a courier who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 */
	public Courier(String name, String surname, String userName, String password) {
		super(name, surname, userName, password);
		this.position = null ;
		this.phoneNumber = "" ;
		this.board = new Board<Order>();

		this.setUserType ("courier") ;
	}
	
	/**
	 * creates a courier who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param position : the position of the courier
	 * @param phoneNumber : the phone number of the courier
	 */
	public Courier(String name, String surname, String userName, String password, Position position, String phoneNumber) {
		super(name, surname, userName, password);
		this.position = position ;
		this.phoneNumber = phoneNumber ;
		this.board = new Board<Order>();
		
		this.setUserType ("courier") ;
	}
	
	/**
	 * accept or refuse a delivery call
	 * @param decision : the decision "true" if accept, "false" if refuse
	 * @param waitingOrder : the waiting order
	 * @param myFoodora
	 */
	public void acceptDeliveryCall (boolean decision, Order waitingOrder, MyFoodora myFoodora){
		if (decision){
			waitingOrder.validateOrderByCourier(myFoodora);
		}else{
			//we set the state of the courier as "off-duty" to allocate another courier to the order
			this.onDuty = false;
			myFoodora.getDeliveryPolicy().allocateCourierToOrder(myFoodora, waitingOrder);
		}
		this.board.removeObs(waitingOrder);
		
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isOnDuty() {
		return onDuty;
	}

	public void setOnDuty(boolean onDuty) {
		this.onDuty = onDuty;
	}

	public int getCounter() {
		return counter;
	}
	
	public void increaseCounter() {
		counter++;
	}

	public Board<Order> getBoard() {
		return board;
	}
}
