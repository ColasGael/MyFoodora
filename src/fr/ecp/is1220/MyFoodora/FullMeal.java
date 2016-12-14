package fr.ecp.is1220.MyFoodora;

public class FullMeal extends Meal {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8140577551997236661L;
	/**
	 * the starter
	 */
	private Starter starter;
	/**
	 * the dessert
	 */
	private Dessert dessert;

	/**
	 * creates a FullMeal object of a given mainDish, starter and dessert
	 * @param name : the name of the meal
	 */
	public FullMeal(String name) {
		super(name);
		this.starter = null;
		this.dessert = null;
	}	
	
	/**
	 * creates a FullMeal object of a given mainDish, starter and dessert
	 * @param name : the name of the meal
	 * @param mainDish
	 * @param starter
	 * @param dessert
	 */
	public FullMeal(String name, MainDish mainDish, Starter starter, Dessert dessert) {
		super(name, mainDish);
		this.starter = starter;
		this.dessert = dessert;
		
		//the price is computed from the prices of the dishes and the discount factor
		this.price = this.computePrice();
		
		//the type of the meals depends on the type of the dishes which compose the meal
		if ((mainDish.getType()== starter.getType()) && (mainDish.getType()== dessert.getType())){
			this.type = mainDish.getType();
		}
	}
	
	/**
	 * computes the price of the meal
	 */
	@Override
	public double computePrice() {
		double price = this.mealVisitor.computePriceMeal(this);
		return(price);
	}

	/**
	 * updates the price of the meal according to the changes of discount factors
	 * @param menu : the menu which contains the meal
	 */
	@Override
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
		this.price = this.computePrice();
	}
	
	/**
	 * adds a dish to the meal if possible
	 * @param dish : the dish we want to add to the meal
	 * @throws NoPlaceInMealException
	 */
	@Override
	public void addDish(Dish dish) throws NoPlaceInMealException {
		this.mealVisitor.addDish2Meal(dish, this);
	}
	
	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public Starter getStarter() {
		return starter;
	}

	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}

	public Dessert getDessert() {
		return dessert;
	}
	
	@Override
	public String toString() {
		return ("Full" + super.toString() + starter + "/n" + dessert + "/n");
	}
}
