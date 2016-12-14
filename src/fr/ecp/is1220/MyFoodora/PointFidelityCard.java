package fr.ecp.is1220.MyFoodora;

public class PointFidelityCard extends FidelityCard {

	private static final long serialVersionUID = -8859809521402683700L;
	/**
	 * the number of fidelity points
	 */
	private int points = 0 ;
	/**
	 * the number of euros spent which gave 1 fidelity point
	 */
	private static double conversion = 10 ;
	
	/**
	 * the number of points to reach in order to get a discount in the next order
	 */
	private static int targetPoints = 100 ;
	
	/**
	 * the rate of the discount applied when the targetPoints value is reached
	 */
	private static double discountFactor = 0.1 ; 
	
	public PointFidelityCard() {
		this.type = "point" ;
	}
	
	/**
	 * computes the reduction of an order according to the fidelity points of the user
	 * @param order : the order submitted by the user
	 * @return reduction : the reduction which can be applied
	 */
	@Override
	public double computeReduction(Order order){
		double reduction = 0;
		if (this.points > targetPoints){
			reduction = order.getPrice()*(1-discountFactor);
		}
		return reduction;
	}
	
	/**
	 * applies the reduction calculated with computeReduction to the price of the order
	 * 		and remove the used fidelity points
	 * @param order : the order submitted by the user
	 */
	@Override
	public void applyReduction (Order order){
		double originalPrice = order.getPrice();
		double reduction = this.computeReduction(order);
		
		order.setPrice(originalPrice - reduction);
		this.removePoints(targetPoints);
	}
		
	
	public int getPoints() {
		return points;
	}

	public void addPoints(int points){
		this.points += points ;
	}
	
	public void removePoints(int points){
		this.points -= points;
	}
	
	/**
	 * converts a price paid in euros in fidelity points
	 * @param price : the price paid
	 * @return points : the number of fidelity points earned
	 */
	public int convertToPoints (double price){
		int points = (int) (price/conversion);
		return points;
	}
	
	/**
	 * adds the fidelity points to the card according to the price of the order
	 * @param order : the order submitted by the customer
	 */
	public void computeFidelityPoints(Order order){
		double price = order.getPrice();
		this.addPoints(this.convertToPoints(price));
	}

	public static double getConversion() {
		return conversion;
	}

	public static void setConversion(double conversion) {
		PointFidelityCard.conversion = conversion;
	}

	public static int getTargetPoints() {
		return targetPoints;
	}

	public static void setTargetPoints(int targetPoints) {
		PointFidelityCard.targetPoints = targetPoints;
	}

	public static double getDiscountFactor() {
		return discountFactor;
	}

	public static void setDiscountFactor(double discountFactor) {
		PointFidelityCard.discountFactor = discountFactor;
	}
	
	@Override
	public String toString(){
		String result = ((FidelityCard)this).toString();
		result += "You have " + this.points + " fidelity points on your card\n";
		result += "You need " + targetPoints + " to have a " + discountFactor + " percent reduction on the price of your next order\n";
		result += "When you spend " + conversion + " euros on My Foodora, you earn 1 fidelity point\n";
		
		return(result);
	}
}
