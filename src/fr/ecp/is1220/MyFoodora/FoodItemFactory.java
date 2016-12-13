package fr.ecp.is1220.MyFoodora;

public class FoodItemFactory {

	public FoodItemFactory() {
	}
	
	/**
	 * create a new dish of type dishType
	 * @param dishType : "starter", "mainDish" or "dessert"
	 * @param name : the name of the dish
	 * @param price : the price of the dish
	 * @param type : the type of dish "standard", "vegetarian" or "glutenFree"
	 * @return dish : the dish
	 */
	public Dish createDish (String dishType, String name, double price, String type) {
		Dish dish = null;
		switch(dishType){
			case("starter"):
				dish = new Starter(name, price, type);
				break;
			case("mainDish"):
				dish = new MainDish(name, price, type);
				break;
			case("dessert"):
				dish = new Dessert(name, price, type);
				break;
		}
		return (dish);
	}
	
	/**
	 * create a new meal : a full-meal or a half-meal
	 * @param mealType : "full" or "half"
	 * @param name : the name of the meal
	 * @return meal : the meal
	 */
	public Meal createMeal (String mealType, String name) {
		Meal meal = null;
		switch(mealType){
			case("full"):
				meal = new FullMeal(name);
				break;
			case("half"):
				meal = new HalfMeal(name);
				break;
		}
		return (meal);
	}

}
