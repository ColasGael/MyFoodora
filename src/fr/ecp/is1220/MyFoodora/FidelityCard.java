package Group9_Project_IS1220_part1_Colas_Prabakaran;

public abstract class FidelityCard implements java.io.Serializable {
	
	
	/**
	 * 
	 */
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
	
	

}
