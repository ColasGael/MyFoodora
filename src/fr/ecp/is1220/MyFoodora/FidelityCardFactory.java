package fr.ecp.is1220.MyFoodora;

import java.io.Serializable ;

public class FidelityCardFactory implements Serializable {
	
	private static final long serialVersionUID = 3053874932358397747L;

	public FidelityCardFactory() {
	}
	
	/**
	 * creates a Fidelity Card of a given type : BasicFidelityCard, PointFidelityCard, LotteryFidelityCard or Null to unregister
	 * @param cardType : "basic", "point", "lottery" or "none" to unregister
	 * @return fidelityCard : the Fidelity Card created
	 */
	public FidelityCard createFidelityCard (String cardType){
		FidelityCard fidelityCard = null;
		
		switch(cardType){
			case("basic"):
				fidelityCard = new BasicFidelityCard();
				break;
			case("point"):
				fidelityCard = new PointFidelityCard();
				break;
			case("lottery"):
				fidelityCard = new LotteryFidelityCard();
				break;
			default: break;
		}
		return fidelityCard;
	}
}
