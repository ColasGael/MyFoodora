package fr.ecp.is1220.MyFoodora;

public class BasicFidelityCard extends FidelityCard {

	private static final long serialVersionUID = -9008565060563275028L;

	public BasicFidelityCard() {
		this.type = "basic" ;
	}
	
	/**
	 * computes the reduction of an order according to the fidelity points of the user
	 * @param order : the order submitted by the user
	 * @return reduction : the reduction which can be applied
	 */
	public double computeReduction(Order order){
		double reduction = 0;
		return reduction;
	}
	/**
	 * applies the reduction calculated with computeReduction to the price of the order
	 * 		and remove the used fidelity points
	 * @param order : the order submitted by the user
	 */
	public void applyReduction (Order order){
		double originalPrice = order.getPrice();
		double reduction = this.computeReduction(order);
		
		order.setPrice(originalPrice - reduction);
	}
}
