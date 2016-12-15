package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList;

public class Restaurant extends User {
	
	private static final long serialVersionUID = -983973017288067345L;
	private Position address ;
	private Menu menu ;
	
	/**
	 * the number of times an order has been passed to a restaurant 
	 */
	private int counter = 0 ;
	/**
	 * the FoodItemFactory used to create food items : dishes or meals
	 */
	private FoodItemFactory foodItemFactory ;
	
	private SorterFoodItem shippedOrderPolicy = new SorterCounter() ;
	
	/**
	 * creates a restaurant who will be used in the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 */
	public Restaurant(String name, String surname, String userName, String password) {
		super(name, "", userName, password);
		this.address = null ;
		this.menu = new Menu() ;
		this.foodItemFactory = new FoodItemFactory();
		
		this.setUserType ("restaurant") ;
	}
	
	/**
	 * creates a restaurant who will be used in the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param address : the address of the restaurant
	 */
	public Restaurant(String name, String userName, String password, Position address) {
		super(name, "", userName, password);
		this.address = address ;
		this.menu = new Menu() ;
		this.foodItemFactory = new FoodItemFactory();

		this.setUserType ("restaurant") ;
	}
	
	/**
	 * adds a new dish to the restaurant menu
	 * @param dishType : "starter", "mainDish" or "dessert"
	 * @param name : the name of the dish
	 * @param price : the price of the dish
	 * @param type : the type of dish "standard", "vegetarian" or "glutenFree"
	 */
	public void addDish(String dishType, String name, double price, String type){
		Dish dish = this.foodItemFactory.createDish(dishType, name, price, type);
		this.menu.addDish(dish);
	}
	
	/**
	 * remove dish of given name from the menu
	 * @param name : the name of the dish
	 * @throws FoodItemNotFoundException
	 */
	public void removeDish(String name) throws FoodItemNotFoundException{
		Dish dish = this.findDishByName(name);
		this.menu.removeDish(dish);
	}
	
	/**
	 * finds the dish of given name in the menu
	 * @param name : the name of the dish
	 * @return dish : the dish of given name
	 * @throws FoodItemNotFoundException
	 */
	public Dish findDishByName(String name) throws FoodItemNotFoundException {
		ArrayList<Dish> dishes = this.menu.getDishes();
		for (Dish dish: dishes){
			if (dish.getName().equals(name)){
				return dish;
			}
		}
		throw (new FoodItemNotFoundException(name+" is not found")) ;
	}
	
	/**
	 * adds a new meal to the restaurant menu
	 * @param mealType : "full" or "half" 
	 * @param name : the name of the meal
	 */
	public void addMeal(String mealType, String name){
		Meal meal = this.foodItemFactory.createMeal(mealType, name);
		this.menu.addMeal(meal);
	}
	
	/**
	 * remove meal of given name from the menu
	 * @param name : the name of the meal
	 * @throws FoodItemNotFoundException
	 */
	public void removeMeal(String name) throws FoodItemNotFoundException{
		Meal meal = this.findMealByName(name);
		this.menu.removeMeal(meal);
	}
	
	/**
	 * finds the meal of given name in the menu
	 * @param name : the name of the meal
	 * @return meal : the meal of given name
	 * @throws FoodItemNotFoundException
	 */
	public Meal findMealByName(String name) throws FoodItemNotFoundException {
		ArrayList<Meal> meals = this.menu.getMeals();
		for (Meal meal: meals){
			if (meal.getName().equals(name)){
				return meal;
			}
		}
		throw (new FoodItemNotFoundException(name+" is not found")) ;
	}
	
	/**
	 * adds a dish to a meal if possible
	 * @param mealName : the name of the meal
	 * @param dishName : the name of the dish
	 * @throws NoPlaceInMealException, FoodItemNotFoundException
	 */
	public void addDish2Meal(String mealName, String dishName) throws NoPlaceInMealException, FoodItemNotFoundException{
		Meal meal = this.findMealByName(mealName);
		Dish dish = this.findDishByName(dishName);
		meal.addDish(dish);

	}
	
	/**
	 * sets the new Meal of the week
	 * @param mealName : the name of the new meal of the week
	 * @throws FoodItemNotFoundException
	 */
	public void setMealOfTheWeek(String mealName) throws FoodItemNotFoundException{
		Meal mealOfTheWeek = this.findMealByName(mealName);
		this.getMenu().setMealOfTheWeek(mealOfTheWeek);
	}
	
	/**
	 * display the food items of the menu according to the shipped order policy
	 * @param foodItemType : the type of food items to display : "dish" or "meal"
	 */
	public void displaySortedFoodItems(String foodItemType){
		ArrayList<FoodItem> notSortedFoodItems = new ArrayList<FoodItem>();
		switch(foodItemType){
			case("dish"):
				ArrayList<Dish> dishes = this.menu.getDishes();
				for(FoodItem dish : dishes){
					notSortedFoodItems.add(dish);
				}
				break;
			case("meal"):
				ArrayList<Meal> meals = this.menu.getMeals();
				for(FoodItem meal : meals){
					notSortedFoodItems.add(meal);
				}
				break;
			default: break;
		}
		ArrayList<FoodItem> sortedFoodItems = this.shippedOrderPolicy.sort(notSortedFoodItems);
		System.out.println(sortedFoodItems);
	}
	
	public Menu getMenu(){
		return menu ;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public void displayMenu(){
		System.out.println(this.menu.toString());
	}
	
	public void setGenericDiscountFactor(double genericDiscountFactor) {
		this.menu.setGenericDiscountFactor(genericDiscountFactor);
	}
	
	public void setSpecialDiscountFactor(double specialDiscountFactor) {
		this.menu.setSpecialDiscountFactor(specialDiscountFactor);
	}

	public Position getAddress() {
		return address;
	}

	public void setAddress(Position address) {
		this.address = address;
	}
	
	public int getCounter() {
		return counter;
	}

	public void increaseCounter() {
		this.counter++ ;
	}
		
	@Override
	public String toString(){
		return ("Name : " + this.getName() + "\n" + 
				"	Address " + address);
	}
}
