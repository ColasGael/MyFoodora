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
	 * 
	 * @param order
	 * @return
	 */
	public abstract double computeReduction(Order order);
	public abstract double applyReduction (Order order);
	
}
