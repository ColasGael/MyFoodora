package fr.ecp.is1220.MyFoodora;


import java.util.ArrayList ;
import java.util.Calendar;
import java.io.* ;


public class MyFoodora implements java.io.Serializable{
	
	private static final long serialVersionUID = -6956532311204306476L;
	/**
	 * The list of all the users of the platform
	 */
	private ArrayList<User> users ;
	/**
	 * The list of all the completed orders
	 */
	private ArrayList<Order> completedOrders ;
	/**
	 * The service-fee value
	 */
	private double serviceFee ;
	/**
	 * The markup-percentage value
	 */
	private double markupPercentage ;
	/**
	 * The delivery cost
	 */
	private double deliveryCost ;
	
	private TargetProfitPolicy targetProfitPolicy = null ;
	
	private DeliveryPolicy deliveryPolicy = null ;
	
	private SorterFoodItem shipOrderPolicy = null ;
	
	/**
	 * Create a new MyFoodora instance
	 * @param serviceFee
	 * @param markupPercentage
	 * @param deliveryCost
	 */
	public MyFoodora(double serviceFee, double markupPercentage, double deliveryCost) {
		super();
		this.users = new ArrayList<User>();
		this.completedOrders = new ArrayList<Order>();
		this.serviceFee = serviceFee;
		this.markupPercentage = markupPercentage;
		this.deliveryCost = deliveryCost;
	}

	public static void saveMyFoodora(MyFoodora myFoodora){
		try { 
			FileOutputStream fileOut = new FileOutputStream("myFoodora.ser"); 
			ObjectOutputStream out = new ObjectOutputStream(fileOut); 
			out.writeObject(myFoodora); 
			out.close(); 
			fileOut.close(); 
			System.out.print("The MyFoodora platform has been saved."); 
		}catch(IOException exception) { 
				exception.printStackTrace(); 
		}
	}
	
	public static MyFoodora loadMyFoodora(){
		MyFoodora myFoodora = null;
		try { 
			FileInputStream fileIn = new FileInputStream("myFoodora.ser"); 
			ObjectInputStream in = new ObjectInputStream(fileIn);  
			myFoodora =(MyFoodora) in.readObject();
			in.close(); 
			fileIn.close();
		}catch(IOException exception) {
			exception.printStackTrace();
		}catch(ClassNotFoundException c) {
			System.out.println("MyFoodora class not found");
			c.printStackTrace();
		}finally{
			return myFoodora;
		}
	}
	
	/**
	 * Method which returns the user who is logged with the given username and password
	 * @param userName
	 * @param password
	 * @return User
	 */
	public User login(String userName, String password) throws IdentificationIncorrectException, AccountDeactivatedException{
		User foundUser;
		for (User user : users){
			//Check that the given identification parameters match a user of the database
			if((user.getUserName()==userName)&&(user.getPassword()==password)){
				foundUser = user ;
				if (foundUser.isActivated()){
					return (foundUser);
				}else{
					throw (new AccountDeactivatedException ("your account is deactivated"));
				}
			}
		}
		throw (new IdentificationIncorrectException ("username or password incorrect"));
	}
	
	public void registerCustomer(String name, String surname, String userName, String password, Position address){
		Customer newUser = new Customer(name, surname, userName, password, address);
		users.add(newUser) ;
	}
	
	public void registerCourier(String name, String surname, String userName, String password, Position position, String phoneNumber){
		Courier newUser = new Courier(name, surname, userName, password, position, phoneNumber);
		users.add(newUser) ;
	}
	
	public void registerManager(String name, String surname, String userName, String password, Position position, String phoneNumber){
		Courier newUser = new Courier(name, surname, userName, password, position, phoneNumber);
		users.add(newUser) ;
	}
	
	
	public void registerRestaurant(String name, String userName, String password, MyFoodora myFoodora, Position address){
		Restaurant newUser = new Restaurant(name, userName, password, address);
		users.add(newUser) ;
	}
	
	/**
	 * creates a new offer which will be shown to the Customers who gave their consensus to be notified
	 * @param offer : the offer which is created
	 */
	public void newOffer(String offer){
		for(User customer : users){
			if(customer instanceof Customer){
				((Customer)customer).addOffer(offer) ;
			}
		}
	}
	
	/**
	 * compute the total income in the last month
	 * @return the total income in the last month
	 */
	public double totalIncomeLastMonth(){
		//get the current date
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		
		//get the number of last month
		date1.add(Calendar.MONTH, -1);
		date2.add(Calendar.MONTH, -1);


		//get the number of days in last month
		int lastDayOfLastMonth = date2.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		date1.set(Calendar.DAY_OF_MONTH, 1);
		date2.set(Calendar.DAY_OF_MONTH, lastDayOfLastMonth);
		double totalIncome = totalIncome (date1, date2);
		return totalIncome ;
	}
	
	/**
	 * compute the total income on a given period : between date1 (day1/month1/year1) and date2 (day2/month2/year2)
	 * @param calendar1 : the beginning date of the period
	 * @param calendar1 : the last date of the period
	 * @return the total income on the given period
	 */
	public double totalIncome(Calendar calendar1, Calendar calendar2){
		double totalIncome = 0 ;
		for(Order order : this.completedOrders){
			Calendar dateOfOrder = order.getDateOfOrder();
			//check that the order was on the given period
			if ((dateOfOrder.compareTo(calendar1)>=0)&&(dateOfOrder.compareTo(calendar1)<=0)){
				totalIncome += order.getPrice()*(1+markupPercentage)+serviceFee ;
			}
		}
		return totalIncome;
	}
	
	/**
	 * compute the total profit in the last month
	 * @return the total profit in the last month
	 */
	public double totalProfitLastMonth(){
		//get the current date
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		
		//get the number of last month
		date1.add(Calendar.MONTH, -1);
		date2.add(Calendar.MONTH, -1);


		//get the number of days in last month
		int lastDayOfLastMonth = date2.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		date1.set(Calendar.DAY_OF_MONTH, 1);
		date2.set(Calendar.DAY_OF_MONTH, lastDayOfLastMonth);
		double totalProfit = totalProfit (date1, date2);
		return totalProfit ;
	}
	
	/**
	 * compute the total profit on a given period : between date1 (day1/month1/year1) and date2 (day2/month2/year2)
	 * @param calendar1 : the beginning date of the period
	 * @param calendar1 : the last date of the period
	 * @return the total income on the given period
	 */
	public double totalProfit (Calendar calendar1, Calendar calendar2){
		double totalProfit = 0 ;
		for(Order order : this.completedOrders){
			Calendar dateOfOrder = order.getDateOfOrder();
			//check that the order was on the given period
			if ((dateOfOrder.compareTo(calendar1)>=0)&&(dateOfOrder.compareTo(calendar1)<=0)){
				totalProfit += order.getPrice()*markupPercentage+serviceFee-deliveryCost ;
			}
		}
		return totalProfit;
	}
	
	public double averageIncomePerCostumer (Calendar calendar1, Calendar calendar2){
		double totalIncome = totalIncome(calendar1, calendar2);
		ArrayList<Customer> customersOfThePeriod = new ArrayList<Customer>();
		
		for(Order order : this.completedOrders){
			Customer customer = order.getCustomer();
			//check that the customer has not already been added
			if (!(customersOfThePeriod.contains(customer))){
				customersOfThePeriod.add(customer);
			}
		}
		return totalIncome/customersOfThePeriod.size();
	}
	
	
	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public void displayUsers(){
		System.out.println("Users " + users);
	}
	
	public void addUser (User user){
		this.users.add(user);
	}
	
	public void removeUser (int uniqueID){
		try{
			User user = this.findUserByUniqueID(uniqueID);
			this.users.remove(user);
		}catch(UserNotFoundException e){
			System.err.println(e);
		}
	}
	
	public User findUserByUniqueID (int uniqueID) throws UserNotFoundException {
		for (User user : this.users){
			if (user.getUniqueID() == uniqueID){
				return (user);
			}
		}
		throw(new UserNotFoundException("User of ID " + uniqueID + " not in the system"));
	}
	
	
	public ArrayList<Order> getCompletedOrders() {
		return completedOrders;
	}


	public void setCompletedOrders(ArrayList<Order> completedOrders) {
		this.completedOrders = completedOrders;
	}
	
	
	public double getServiceFee() {
		return serviceFee;
	}
	
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getMarkupPercentage() {
		return markupPercentage;
	}
	
	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}
	


	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}


	public double getDeliveryCost() {
		return deliveryCost;
	}




	public TargetProfitPolicy getTargetProfitPolicy() {
		return targetProfitPolicy;
	}


	public void setTargetProfitPolicy(TargetProfitPolicy targetProfitPolicy) {
		this.targetProfitPolicy = targetProfitPolicy;
	}


	
	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}


	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}

	public SorterFoodItem getShipOrderPolicy() {
		return shipOrderPolicy;
	}


	public void setShipOrderPolicy(SorterFoodItem shipOrderPolicy) {
		this.shipOrderPolicy = shipOrderPolicy;
	}
	
}
