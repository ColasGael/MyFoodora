package fr.ecp.is1220.MyFoodora;

public class MyFoodoraExample {

	/**
	 * creates an example of My Foodora which will be saved in "MyFoodora.ser"
	 * this example is later used in the JUnit tests
	 */
	public void createMyFoodora() {
		//we create an empty myFoodora
		double serviceFee = 10;
		double markupPercentage = 0.05;
		double deliveryCost = 5;
		MyFoodora myFoodora = new MyFoodora(serviceFee, markupPercentage, deliveryCost);
		
		//we add the first manager
		Manager manager = new Manager ("")
		
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
		
		//we adds manager
		
		
		
		myFoodora.register("customer","Theo", "Bob","theo7794","password");
		myFoodora.register("courier","Jean", "Livreur","jean","password");
		myFoodora.register("restaurant","Le Hoki",null,"hoki","password");
		
		myFoodora.saveMyFoodora();
		MyFoodora myFoodorabis = MyFoodora.loadMyFoodora() ;
	}
}
