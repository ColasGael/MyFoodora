package fr.ecp.is1220.MyFoodora;

import java.util.ArrayList;
import java.util.Calendar ;
import java.util.Locale;

public class Order implements java.io.Serializable {
	
	private static final long serialVersionUID = -372769376339718757L;
	
	/**
	 * the static lastID ensure that the IDs of all orders are different
	 */
	protected static int lastID = 1 ;
	/**
	 * the unique ID of the order
	 */
	private int uniqueID;
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
	 * @param customer : the customer making an order
	 * @param restaurant : the target restaurant
	 */
	public Order(Customer customer, Restaurant restaurant) {
		this.uniqueID = lastID;
		lastID++;
		
		this.customer = customer;
		this.restaurant = restaurant;
		this.dishes = new ArrayList<Dish>();
		this.meals = new ArrayList<Meal>();
		this.dateOfOrder = Calendar.getInstance() ;
		this.courier = null ;
		this.addressOfDelivery = customer.getAddress() ;
	}

	public int getUniqueID() {
		return uniqueID;
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

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public double computePrice(){
		double price = 0;
		
		for(Dish dish:this.dishes){
			price += dish.getPrice();
		}
		for(Meal meal:this.meals){
			price += meal.getPrice();
		}
		return(price);
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
		//we put the order on the board of the courier
		this.courier.getBoard().addObs(this);
	}

	/**
	 * when the order is validated
	 * 		we allocate a courier and put the order on his board
	 * 		we increase the counter of the restaurant
	 * 		we increase all the counters of the picked items
	 * 		we compute the price of the order according to a eventual reduction
	 * 		if necessary we add the fidelity points to the card of the customer
	 * @param applyReduction : "true" if the customer wants to apply a reduction using his fidelity card
	 * @param myFoodora : myFoodora core
	 */
	public void submit(boolean applyReduction, MyFoodora myFoodora){
		myFoodora.getDeliveryPolicy().allocateCourierToOrder(myFoodora, this);
		
		restaurant.increaseCounter();
		for (Dish dish : dishes){
			dish.increaseCounter();
		}
		for (Meal meal : meals){
			meal.increaseCounter();
		}
		this.price = this.computePrice();
		
		FidelityCard fidelityCard = customer.getFidelityCard();
		if(applyReduction){
			fidelityCard.applyReduction(this);	
		}
		if (fidelityCard instanceof PointFidelityCard){
			((PointFidelityCard)fidelityCard).computeFidelityPoints(this);
		}
	}
	
	/**
	 * indicate that the courier has accepted the delivery call of this order
	 * 		the order is then completed
	 * @param myFoodora : my Foodora system
	 */
	public void validateOrderByCourier (MyFoodora myFoodora){
		this.courier.increaseCounter();
		myFoodora.addCompletedOrders(this);
	}

	@Override
	public String toString() {
		return "Order [ID : " + this.uniqueID + "\n"
				+ "dateOfOrder=" + dateOfOrder.get(Calendar.DAY_OF_MONTH) + " " + dateOfOrder.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + dateOfOrder.get(Calendar.YEAR) + "\n" 
				+ "customer=" + customer + "\n" 
				+ "restaurant=" + restaurant + "\n" 
				+ "dishes=" + dishes + "\n"
				+ "meals=" + meals + "\n"
				+ "price=" + price + "\n"
				+ "courier=" + courier + "\n"
				+ "addressOfDelivery=" + addressOfDelivery + "]\n";
	}
	
	@Override
	public boolean equals (Object o){
		boolean isequal = false;
		if (o instanceof Order){
			Order order = (Order)o;
			isequal = (this.uniqueID == order.getUniqueID());
		}
		return isequal;
	}
}
