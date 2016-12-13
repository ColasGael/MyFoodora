package fr.ecp.is1220.MyFoodora;

public abstract class FidelityCard implements java.io.Serializable {
	
	private static final long serialVersionUID = -8552095078324661097L;
	/**
	 * the type of the fidelity card : basic, point or lottery
	 */
	protected String type ;
	
	/**
	 * creates a fidelity card
	 */
	public FidelityCard() {
	}
	
	public String getType() {
		return type;
	}
	
	/**
	 * computes the reduction of an order according to the fidelity program of the user
	 * @param order : the order submitted by the user
	 * @return reduction : the reduction which can be applied
	 */
	public abstract double computeReduction(Order order);
	/**
	 * applies the reduction calculated with computeReduction to the price of the order
	 * @param order : the order submitted by the user
	 */
	public abstract void applyReduction (Order order);
	
}
