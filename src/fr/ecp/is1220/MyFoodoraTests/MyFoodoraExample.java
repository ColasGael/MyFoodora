package fr.ecp.is1220.MyFoodoraTests;

import fr.ecp.is1220.MyFoodora.*;

public class MyFoodoraExample {

	/**
	 * creates an example of My Foodora which will be saved in "MyFoodora.ser"
	 * this example is later used in the JUnit tests
	 */
	public static void createMyFoodora() {
		//we create an empty myFoodora
		double serviceFee = 10;
		double markupPercentage = 0.05;
		double deliveryCost = 5;
		MyFoodora myFoodora = new MyFoodora(serviceFee, markupPercentage, deliveryCost);
		
		//we add the first manager
		Manager manager = new Manager("Gael", "Colas", "gcolas", "0123456789", myFoodora);
		myFoodora.addUser(manager);
		
		//we create a first restaurant : the HOKI
		Restaurant hoki = new Restaurant("Le Hoki", "hoki", "password", new Position(12.3, 478.21));
		//we add dishes to the menu
		hoki.addDish("mainDish", "maki thon", 4.5, "standard");
		hoki.addDish("mainDish", "maki saumon", 4, "standard");
		hoki.addDish("mainDish", "maki cheese", 3.5, "vegetarian");
		hoki.addDish("mainDish", "california maki", 16.5, "standard");
		hoki.addDish("starter", "soupe", 2.5, "vegetarian");
		hoki.addDish("dessert", "litchee", 4, "vegetarian");
		hoki.addDish("mainDish", "brochettes boeuf", 10.5, "standard");
		//we add meals to the menu
		hoki.addMeal("half", "M2");
		hoki.addDish2Meal("M2", "soupe");
		hoki.addDish2Meal("M2", "maki saumon");
		hoki.addMeal("half", "M2");
		hoki.addDish2Meal("M3", "soupe");
		hoki.addDish2Meal("M3", "maki cheese");
		hoki.addDish2Meal("M3", "litchee");
		hoki.addMeal("half", "B1");
		hoki.addDish2Meal("B1", "brochettes boeuf");
		hoki.addDish2Meal("B1", "litchee");
		//we set the meal of the day
		hoki.setMealOfTheWeek("B1");
		//we add the hoki to myFoodora
		myFoodora.addUser(hoki);
		
		//we create a second restaurant : the DOMINOS
		Restaurant dominos = new Restaurant("Dominos", "domi", "password", new Position(121.3, 41.31));
		//we add dishes to the menu
		dominos.addDish("mainDish", "cannibale", 13, "standard");
		dominos.addDish("mainDish", "margherita", 10, "vegetarian");
		dominos.addDish("mainDish", "orientale", 13.5, "standard");
		dominos.addDish("mainDish", "4 fromages", 12.5, "vegetarian");
		dominos.addDish("starter", "cheesy bread", 2.5, "vegetarian");
		dominos.addDish("dessert", "tiramisu", 4.5, "vegetarian");
		//we add meals to the menu
		dominos.addMeal("half", "duo");
		dominos.addDish2Meal("duo", "cheesy bread");
		dominos.addDish2Meal("duo", "orientale");
		dominos.addMeal("full", "family");
		dominos.addDish2Meal("family", "cannibale");
		dominos.addDish2Meal("family", "cheesy bread");
		dominos.addDish2Meal("family", "tiramisu");
		//we add the dominos to myFoodora
		myFoodora.addUser(dominos);
		
		//we adds manager
		manager.addUser("manager", "Sylvestre", "Prabakaran", "prabakarans", "birthdaydate");
		
		//we add couriers
		myFoodora.addUser(new Courier("Peter", "Parker", "spiderman", "password", new Position(1.23, 854.1), "0651964987"));
		myFoodora.addUser(new Courier("Spider", "Man", "peterparker", "motdepasse", new Position(1.23, 854.1), "0651964988"));
		myFoodora.addUser(new Courier("Toby", "McGuire", "tobmac", "azerty", new Position(12.3, 5.18), "0651964989"));
		
		//we add customers
		myFoodora.addUser(new Customer("Xavier", "Collar", "chaperouge", "qsdfghjkl", new Position(23.3, 45.12), "xavier.collar@myecp.fr", "0786822354"));
		myFoodora.addUser(new Customer("Regis", "Troissant", "cptcroche", "hfekljkfemvc", new Position(87.3, 12.5), "regis.troissant@myecp.fr", "0786822355"));
		myFoodora.addUser(new Customer("Baptiste", "Turpin", "alladin", "ieupri", new Position(2.3, 4.12), "baptiste.turpin@myecp.fr", "0786822356"));
		myFoodora.addUser(new Customer("Kevin", "Uzan", "PJ", "fpzkfpzof", new Position(69.2, 12.78), "kevin.uzan@myecp.fr", "0786822354"));
		myFoodora.addUser(new Customer("Theo", "Bob", "theo7794", "password", new Position(84.3, 145.12), "theo.bob@gmail.com", "0786822354"));
		
		myFoodora.saveMyFoodora();
	}
	
	
	public static void main(String[] args) {
		createMyFoodora();
	}
}
