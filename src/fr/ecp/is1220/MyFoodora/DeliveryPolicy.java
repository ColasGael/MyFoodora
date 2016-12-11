package Group9_Project_IS1220_part1_Colas_Prabakaran;

public interface DeliveryPolicy {
	
	/**
	 * Allocates a courier to the order according to one of the two policy : fastest delivery or fair-occupation delivery
	 * @param myFoodora : MyFoodora system
	 * @param order : the order which need to be allocated to a courier
	 */
	public abstract void allocateCourierToOrder(MyFoodora myFoodora, Order order);
}
