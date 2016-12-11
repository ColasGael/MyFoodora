package fr.ecp.is1220.MyFoodora;

public class HalfMeal extends Meal {
	
	private static final long serialVersionUID = 5964746563078278289L;
	/**
	 * the side dish : a starter or a dessert
	 */
	private Dish sideDish;
	
	/**
	 * creates a HalfMeal object of a given mainDish and sideDish
	 * @param name : the name of the meal
	 * @param mainDish
	 * @param sideDish
	 */
	public HalfMeal(String name, MainDish mainDish, Dish sideDish) {
		super(name, mainDish);
		this.sideDish = sideDish;
		
		//the type of the meals depends on the type of the dishes which compose the meal
		if (mainDish.getType()== sideDish.getType()){
			this.type = mainDish.getType();
		}
		
		//the price is computed from the prices of the dishes and the discount factor
		this.price = (sideDish.getPrice() + mainDish.getPrice())*(1-this.discountFactor);
	}
	
	/**
	 * updates the price of the meal according to the changes of discount factors
	 * @param menu : the menu which contains the meal
	 */
	public void update (Menu menu){
		double genericDiscountFactor = menu.getGenericDiscountFactor();
		double specialDiscountFactor = menu.getSpecialDiscountFactor();
		Meal mealOfTheWeek = menu.getMealOfTheWeek();
		
		if (this == mealOfTheWeek){
			//we apply the special discount factor
			this.discountFactor = specialDiscountFactor;
		}
		else{
			//we apply the generic discount factor
			this.discountFactor = genericDiscountFactor;
		}
		this.price = (sideDish.getPrice() + mainDish.getPrice())*(1-this.discountFactor);
	}
	

	public Dish getSideDish() {
		return sideDish;
	}

	@Override
	public String toString() {
		return ("Half" + ((Meal)this).toString() + sideDish.toString() + "/n");
	}
	
	
}
