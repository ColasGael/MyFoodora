package fr.ecp.is1220.MyFoodora;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 5554256869096417785L;


	public OrderNotFoundException() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param arg0 : commentaire
	 */
	public OrderNotFoundException(String arg0) {
		super(arg0);
	}
}