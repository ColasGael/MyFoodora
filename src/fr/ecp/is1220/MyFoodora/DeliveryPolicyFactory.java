package fr.ecp.is1220.MyFoodora;


public class DeliveryPolicyFactory {

	public DeliveryPolicyFactory() {
	}
	
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