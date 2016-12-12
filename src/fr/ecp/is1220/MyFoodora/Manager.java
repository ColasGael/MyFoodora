package fr.ecp.is1220.MyFoodora;

import java.util.Calendar;

public class Manager extends User {
	
	private static final long serialVersionUID = 2291793873364057722L;
	/**
	 * the MyFoodora core
	 */
	private MyFoodora myFoodora ;
	
	private TargetProfitPolicyFactory targetProfitPolicyFactory ;
	private DeliveryPolicyFactory deliveryPolicyFactory ;
	
	/**
	 * creates a manager who will manage the MyFoodora platform
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param myFoodora : the myFoodora core
	 */
	public Manager(String name, String surname, String userName, String password, MyFoodora myFoodora) {
		super(name, surname, userName, password);
		this.myFoodora = myFoodora;
		this.targetProfitPolicyFactory = new TargetProfitPolicyFactory();
		this.deliveryPolicyFactory = new DeliveryPolicyFactory();
		
		this.setUserType("manager");
	} 
	
	/**
	 * adds the user 
	 * @param user : The user object 
	 */
	public void addUser(String userType, String name, String surname, String userName, String password){
		myFoodora.getUserFactory().registerUser(userType, name, surname, userName, password, myFoodora);
		
		//to ensure that only managers can create manager accounts
		if (userType.equals("manager")){
			try{
				myFoodora.login(userName, password).setActivated(true);
			}catch(Exception e){
			}
		}
	}
	
	/**
	 * remove the user of uniqueID from the system
	 * @param uniqueID
	 */
	public void removeUser(int uniqueID){
		myFoodora.removeUser(uniqueID);
	}
	
	/**
	 * activate the user of uniqueID of the system
	 * @param uniqueID
	 */
	public void activateUser (int uniqueID){
		try{
			User user = myFoodora.findUserByUniqueID(uniqueID);
			user.setActivated(true);
		}catch(UserNotFoundException e){
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * deactivate the user of uniqueID of the system
	 * @param uniqueID
	 */
	public void deactivateUser (int uniqueID){
		try{
			User user = myFoodora.findUserByUniqueID(uniqueID);
			user.setActivated(false);
		}catch(UserNotFoundException e){
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * compute the total income on a given period : between date1 (day1/month1/year1) and date2 (day2/month2/year2)
	 * @param calendar1 : the beginning date of the period
	 * @param calendar1 : the last date of the period
	 * @return the total income on the given period
	 */
	public double totalIncome(Calendar calendar1, Calendar calendar2){
		return(myFoodora.totalIncome(calendar1, calendar2));
	}
	
	/**
	 * compute the total profit on a given period : between date1 (day1/month1/year1) and date2 (day2/month2/year2)
	 * @param calendar1 : the beginning date of the period
	 * @param calendar1 : the last date of the period
	 * @return the total income on the given period
	 */
	public double totalProfit (Calendar calendar1, Calendar calendar2){
		return(myFoodora.totalProfit(calendar1, calendar2));
	}	
	
	/**
	 * compute the average income per customer on a given period : between date1 (day1/month1/year1) and date2 (day2/month2/year2)
	 * @param calendar1
	 * @param calendar2
	 * @return the average income per customer on a given period
	 */
	public double averageIncomePerCostumer (Calendar calendar1, Calendar calendar2){
		return(myFoodora.averageIncomePerCostumer(calendar1, calendar2));
	}
	
	/**
	 * compute the value of the profit related information (service fee, markup percentage or delivery cost) to meet a target profit	 
	 * @param profitInfo : the profit related information : "serviceFee", "markup" or "deliveryCost"
	 * @param targetProfit : the target profit to meet
	 * @return profitInfoValue : the value of the profit related information
	 */
	public double meetTargetProfit (String profitInfo, double targetProfit){
		try{
			TargetProfitPolicy targetProfitPolicy = targetProfitPolicyFactory.chooseTargetProfitPolicy(profitInfo);
			double profitInfoValue = targetProfitPolicy.meetTargetProfit(myFoodora, targetProfit);
			return (profitInfoValue);

		}catch(NonReachableTargetProfitException e){
			System.err.println(e.getMessage());
			return (0);
		}
	}
	
	/**
	 * set the delivery policy : fastest delivery policy or fair occupation delivery policy
	 * @param deliveryPolicyChoice : "fastest" or "fairOccupation"
	 */
	public void setDeliveryPolicy (String deliveryPolicyChoice){
		DeliveryPolicy deliveryPolicy = deliveryPolicyFactory.chooseDeliveryPolicy(deliveryPolicyChoice);
		myFoodora.setDeliveryPolicy(deliveryPolicy);
	}
	
	/**
	 * get the most selling restaurant considering the number of orders
	 * @return mostSellingRestaurant : the most selling restaurant
	 */
	public Restaurant mostSellingRestaurant(){
		Restaurant mostSellingRestaurant = null;
		int mostSells = 0;
		
		for (User user : myFoodora.getUsers()){
			if (user.getUserType().equals("restaurant")){
				Restaurant restaurant = (Restaurant)user;
				if(restaurant.getCounter() > mostSells){
					mostSellingRestaurant = restaurant;
					mostSells = restaurant.getCounter();
				}
			}
		}
		return(mostSellingRestaurant);
	}
	
	/**
	 * get the least selling restaurant considering the number of orders
	 * @return leastSellingRestaurant : the least selling restaurant
	 */
	public Restaurant leastSellingRestaurant(){
		Restaurant leastSellingRestaurant = null;
		int leastSells = 999999999;
		
		for (User user : myFoodora.getUsers()){
			if (user.getUserType().equals("restaurant")){
				Restaurant restaurant = (Restaurant)user;
				if(restaurant.getCounter() < leastSells){
					leastSellingRestaurant = restaurant;
					leastSells = restaurant.getCounter();
				}
			}
		}
		return(leastSellingRestaurant);
	}
	
	
	
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}

	public void setMyFoodora(MyFoodora myFoodora) {
		this.myFoodora = myFoodora;
	}
}
