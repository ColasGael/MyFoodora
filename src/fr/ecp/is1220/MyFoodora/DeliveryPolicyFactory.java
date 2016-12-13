package fr.ecp.is1220.MyFoodora;


public class DeliveryPolicyFactory {

	public DeliveryPolicyFactory() {
	}
	
	/**
	 * choose the delivery policy : fastest or fair-occupation delivery policy
	 * @param deliveryPolicyChoice : "fastest" or "fairOccupation"
	 * @return deliveryPolicy : the delivery policy chosen
	 */
	public DeliveryPolicy chooseDeliveryPolicy (String deliveryPolicyChoice) {
		DeliveryPolicy deliveryPolicy = null;
		switch(deliveryPolicyChoice){
			case("fastest"):
				deliveryPolicy = new FastestDeliveryPolicy();
				break;
			case("fairOccupation"):
				deliveryPolicy = new FairOccupationDeliveryPolicy();
				break;
		}
		return (deliveryPolicy);
	}
}