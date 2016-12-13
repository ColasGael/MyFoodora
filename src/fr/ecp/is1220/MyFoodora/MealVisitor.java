package fr.ecp.is1220.MyFoodora;

/**
 * this visitor pattern enables us to deal with different implementation of
 *		computePrice
 *		addDish
 */
public class MealVisitor {
	
	/**
	 * compute the price of a full meal
	 * @param fullMeal
	 * @return price : the price of the full meal
	 */
	public double computePriceMeal(FullMeal fullMeal){
		Starter starter = fullMeal.getStarter();
		MainDish mainDish = fullMeal.getMainDish();
		Dessert dessert = fullMeal.getDessert();
		double discountFactor = fullMeal.getDiscountFactor();
		
		double price = (starter.getPrice() + mainDish.getPrice() + dessert.getPrice())*(1-discountFactor);
		return (price);
	}
	
	/**
	 * compute the price of a half meal
	 * @param halfMeal
	 * @return price : the price of the half meal
	 */
	public double computePriceMeal(HalfMeal halfMeal){
		Dish sideDish = halfMeal.getSideDish();
		MainDish mainDish = halfMeal.getMainDish();
		double discountFactor = halfMeal.getDiscountFactor();
		
		double price = (sideDish.getPrice() + mainDish.getPrice())*(1-discountFactor);
		return (price);
	}
	
	/**
	 * adds a dish to the meal if possible
	 * @param dish : the dish we want to add to the meal
	 * @param fullMeal : the full meal
	 * @throws NoPlaceInMealException
	 */
	public void addDish2Meal(Dish dish, FullMeal fullMeal) throws NoPlaceInMealException{
		Starter starter = fullMeal.getStarter();
		MainDish mainDish = fullMeal.getMainDish();
		Dessert dessert = fullMeal.getDessert();
		
		String dishType = dish.getDishType();
		switch(dishType){
			case("starter"):
				if (starter==null){
					fullMeal.setStarter((Starter)dish);
				}else{
					throw (new NoPlaceInMealException("The meal already contains a starter"));
				}
				break;
			case("mainDish"):
				if (mainDish==null){
					fullMeal.setMainDish((MainDish)dish);
				}else{
					throw (new NoPlaceInMealException("The meal already contains a mainDish"));
				}
				break;
			case("dessert"):
				if (dessert==null){
					fullMeal.setDessert((Dessert)dish);
				}else{
					throw (new NoPlaceInMealException("The meal already contains a dessert"));
				}
				break;
			default: break;
		}
		//the price is computed from the prices of the dishes and the discount factor
		fullMeal.setPrice (fullMeal.computePrice());
		
		//the type of the meals depends on the type of the dishes which compose the meal
		if ((mainDish.getType()== starter.getType()) && (mainDish.getType()== dessert.getType())){
			fullMeal.setType(mainDish.getType());
		}
	}
	
	/**
	 * adds a dish to the meal if possible
	 * @param dish : the dish we want to add to the meal
	 * @param fullMeal : the full meal
	 * @throws NoPlaceInMealException
	 */
	public void addDish2Meal(Dish dish, HalfMeal halfMeal) throws NoPlaceInMealException{
		Dish sideDish = halfMeal.getSideDish();
		MainDish mainDish = halfMeal.getMainDish();
		
		String dishType = dish.getDishType();
		switch(dishType){
			case("starter"):
				if (sideDish==null){
					halfMeal.setSideDish((Starter)dish);
				}else{
					throw (new NoPlaceInMealException("The meal already contains a sideDish"));
				}
				break;
			case("mainDish"):
				if (mainDish==null){
					halfMeal.setMainDish((MainDish)dish);
				}else{
					throw (new NoPlaceInMealException("The meal already contains a mainDish"));
				}
				break;
			case("dessert"):
				if (sideDish==null){
					halfMeal.setSideDish((Dessert)dish);
				}else{
					throw (new NoPlaceInMealException("The meal already contains a sideDish"));
				}
				break;
			default: break;
		}
		//the price is computed from the prices of the dishes and the discount factor
		halfMeal.setPrice(halfMeal.computePrice());
		
		//the type of the meals depends on the type of the dishes which compose the meal
		if (mainDish.getType()== sideDish.getType()){
			halfMeal.setType(mainDish.getType());
		}

	}
}
