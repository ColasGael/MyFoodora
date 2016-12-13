package fr.ecp.is1220.MyFoodora;

public class Dessert extends Dish {
	
	private static final long serialVersionUID = -7229817878820554350L;

	public Dessert(String name, double price, String type) {
		super(name, price, type);
		this.dishType = "dessert";
	}

	@Override
	public String toString() {
		return ("Dessert : " + ((Dish)this).toString());
	}	
}
