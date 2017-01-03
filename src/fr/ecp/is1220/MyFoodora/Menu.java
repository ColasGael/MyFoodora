package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList;

public class Menu implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8318434210469557686L;
	/**
	 * the list of dishes that are available in the menu
	 */
	private ArrayList<Dish> dishes;
	/**
	 * the list of meals that are available in the menu
	 */
	private ArrayList<Meal> meals;
	/**
	 * the generic discount factor for the meal of the week
	 */
	private double genericDiscountFactor = 0.05;
	/**
	 * the special discount factor for the meal of the week
	 */
	private double specialDiscountFactor = 0.1;
	/**
	 * the meal of the week
	 */
	private Meal mealOfTheWeek = null; 
	
	public Menu(){
		this.dishes = new ArrayList<Dish>() ;
		this.meals = new ArrayList<Meal>() ;
	}
	
	/**
	 * creates a Menu object given a list of dishes and meals
	 * @param dishes : the list of dishes to add to the menu
	 * @param meals : the list of meals to add to the menu
	 */
	public Menu(ArrayList<Dish> dishes, ArrayList<Meal> meals) {
		this.dishes = dishes;
		this.meals = meals;
	}

	/**
	 * notifies the meal (of the meals list) of the change of the discount factors
	 */
	public void notifyMeals(){
		for (Meal meal : meals){
			meal.update(this);
		}
	}
	
	public ArrayList<Dish> getDishes() {
		return dishes;
	}
	
	/**
	 * add a dish to the list of dishes proposed on the menu
	 * @param dish : the dish to add to the menu
	 */
	public void addDish(Dish dish){
		this.dishes.add(dish);
	}
	
	/**
	 * remove a dish to the list of dishes proposed on the menu
	 * @param dish : the dish to remove from the menu
	 */
	public void removeDish(Dish dish){
		this.dishes.remove(dish);
	}
	
	public ArrayList<Meal> getMeals() {
		return meals;
	}
	
	/**
	 * add a meal to the list of meals proposed on the menu
	 * @param meal : the meal to add to the menu
	 */
	public void addMeal(Meal meal){
		this.meals.add(meal);
	}
	
	/**
	 * remove a meal to the list of meals proposed on the menu
	 * @param meal : the meal to remove from the menu
	 */
	public void removeMeal(Meal meal){
		this.meals.remove(meal);
	}

	public double getGenericDiscountFactor() {
		return genericDiscountFactor;
	}

	public void setGenericDiscountFactor(double genericDiscountFactor) {
		this.genericDiscountFactor = genericDiscountFactor;
		this.notifyMeals();
	}

	public double getSpecialDiscountFactor() {
		return specialDiscountFactor;
	}

	public void setSpecialDiscountFactor(double specialDiscountFactor) {
		this.specialDiscountFactor = specialDiscountFactor;
		this.notifyMeals();
	}

	public Meal getMealOfTheWeek() {
		return mealOfTheWeek;
	}

	public void setMealOfTheWeek(Meal mealOfTheWeek) {
		this.mealOfTheWeek = mealOfTheWeek;
		this.notifyMeals();
	}

	@Override
	public String toString() {
		return "Menu \n[dishes=\n" + dishes + "\n\nmeals=\n" + meals + "\n\ngenericDiscountFactor : " + genericDiscountFactor
				+ ", \nspecialDiscountFactor : " + specialDiscountFactor + ", \n\nmealOfTheWeek :" + mealOfTheWeek + "]";
	}
	

}
