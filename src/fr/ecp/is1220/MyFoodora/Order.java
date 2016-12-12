package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList;
import java.util.Calendar ;

public class Order implements java.io.Serializable {
	
	private static final long serialVersionUID = -372769376339718757L;
	/**
	 * the date of the order
	 */
	private Calendar dateOfOrder ;
	/**
	 * the customer who makes the order
	 */
	private Customer customer;
	/**
	 * the restaurant chosen by the customer
	 */
	private Restaurant restaurant;
	/**
	 * the list of dishes picked by the customer
	 */
	private ArrayList<Dish> dishes;
	/**
	 * the list of meals picked by the customer
	 */
	private ArrayList<Meal> meals;
	/**
	 * the price of the order
	 */
	private double price;
	/**
	 * the courier who will delivery the order
	 */
	private Courier courier ;
	/**
	 * 
	 */
	private Position addressOfDelivery ;
	
	/**
	 * creates a new Order object given a customer and a target restaurant
	 * @param customer
	 * @param restaurant
	 */
	public Order(Customer customer, Restaurant restaurant) {
		this.customer = customer;
		this.restaurant = restaurant;
		this.dishes = new ArrayList<Dish>();
		this.meals = new ArrayList<Meal>();
		this.dateOfOrder = Calendar.getInstance() ;
		this.courier = null ;
		this.addressOfDelivery = customer.getAddress() ;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	
	public Calendar getDateOfOrder() {
		return dateOfOrder;
	}

	public ArrayList<Dish> getDishes() {
		return dishes;
	}
	
	/**
	 * add a dish to the list of dishes proposed on the menu
	 * @param meal : the dish to add to the menu
	 */
	public void addDish(Dish dish){
		this.dishes.add(dish);
	}
	
	/**
	 * remove a dish to the list of dishes proposed on the menu
	 * @param meal : the dish to remove from the menu
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

	public double getPrice() {
		return price;
	}
	
	
	public Position getAddressOfDelivery() {
		return addressOfDelivery;
	}

	public void setAddressOfDelivery(Position addressOfDelivery) {
		this.addressOfDelivery = addressOfDelivery;
	}	

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	/**
	 * when the order is validated
	 * 		we allocate a courier and increase his counter
	 * 		we increase the counter of the restaurant
	 * 		we increase all the counters of the picked items
	 */
	public void submit(MyFoodora myFoodora){
		myFoodora.getDeliveryPolicy().allocateCourierToOrder(myFoodora, this);
		this.courier.increaseCounter();
		
		restaurant.increaseCounter();
		
		for (Dish dish : dishes){
			dish.increaseCounter();
		}
		for (Meal meal : meals){
			meal.increaseCounter();
		}
		myFoodora.getDeliveryPolicy().allocateCourierToOrder(myFoodora,this);
	}

	@Override
	public String toString() {
		return "Order [dateOfOrder=" + dateOfOrder + ", customer=" + customer + ", restaurant=" + restaurant
				+ ", dishes=" + dishes + ", meals=" + meals + ", price=" + price + ", courier=" + courier
				+ ", addressOfDelivery=" + addressOfDelivery + "]";
	}	
}
