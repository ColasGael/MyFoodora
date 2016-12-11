package fr.ecp.is1220.MyFoodora;

public class Starter extends Dish {

	private static final long serialVersionUID = -8468995702561432088L;

	public Starter(String name, double price, String type) {
		super(name, price, type);
	}
	
	@Override
	public String toString() {
		return ("Starter : " + ((Dish)this).toString());
	}
	
}
