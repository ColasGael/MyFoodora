package fr.ecp.is1220.MyFoodora;

public abstract class Meal extends FoodItem {
	
	private static final long serialVersionUID = 5331853906083701302L;
	/**
	 * the name of the meal
	 */
	private String name;
	/**
	 * the main-dish of the meal
	 */
	protected MainDish mainDish;
	
	/**
	 * the type of the dish : standard, vegetarian or gluten-free
	 */
	protected String type = "standard";
	/**
	 * The discount factor of the dish
	 */
	protected double discountFactor = 0.05; 
	
	protected MealVisitor mealVisitor = new MealVisitor() ;
	
	/**
	 * creates a Meal object of a given mainDish
	 * @param name : the name of the meal
	 */
	public Meal(String name) {
		this.name = name ;
		this.mainDish = null;
	}
	
	/**
	 * creates a Meal object of a given mainDish
	 * @param name : the name of the meal
	 * @param mainDish : the mainDish of the meal
	 */
	public Meal(String name, MainDish mainDish) {
		this.name = name;
		this.mainDish = mainDish;
	}
	
	public abstract double computePrice();
	
	public abstract void update (Menu menu);
	
	public abstract void addDish (Dish dish) throws NoPlaceInMealException;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setMainDish(MainDish mainDish) {
		this.mainDish = mainDish;
	}

	public MainDish getMainDish() {
		return mainDish;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
	public double getDiscountFactor() {
		return discountFactor;
	}

	@Override
	public String toString() {
		return ("Meal : " + this.getName() + "\nprice : " + this.getPrice() + " type : " + this.getType() +"\n"+
				 mainDish);
	}
	
	@Override
	public boolean equals (Object o){
		boolean isequal = false;
		if (o instanceof Meal){
			Meal meal = (Meal)o;
			isequal = this.name.equals(meal.getName());
		}
		return isequal;
	}
}
