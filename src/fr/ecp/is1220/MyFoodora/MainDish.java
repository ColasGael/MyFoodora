package fr.ecp.is1220.MyFoodora;

public class MainDish extends Dish{
	
	private static final long serialVersionUID = -107391360112730425L;

	public MainDish(String name, double price, String type) {
		super(name, price, type);
		this.dishType = "mainDish";
	}
	
	@Override
	public String toString() {
		return ("MainDish : " + super.toString());
	}
}
