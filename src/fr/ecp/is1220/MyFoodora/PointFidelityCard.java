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
	private static double discountPrice = 0.1 ; 
	
	public PointFidelityCard() {
		this.type = "point" ;
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
	
	public int convertToPoints (double price){
		int points = (int) (price/conversion);
		return points;
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

	public static double getDiscountPrice() {
		return discountPrice;
	}

	public static void setDiscountPrice(double discountPrice) {
		PointFidelityCard.discountPrice = discountPrice;
	}
	
	
}
