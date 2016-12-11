package fr.ecp.is1220.MyFoodora;

public class Courier extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5347546696284207206L;
	private Position position ;
	private String phoneNumber ;
	private int counter = 0 ;
	/**
	 * true when the courier is on-duty and false if he is off-duty
	 */
	private boolean onDuty = false ;

	/**
	 * creates a courier who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param position : the position of the courier
	 * @param phoneNumber : the phone number of the courier
	 */
	public Courier(String name, String userName, String password, MyFoodora myFoodora, Position position, String phoneNumber) {
		super(name, userName, password, myFoodora);
		this.position = position ;
		this.phoneNumber = phoneNumber ;
		userType = "Courier" ;
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
}
