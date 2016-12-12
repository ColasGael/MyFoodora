package fr.ecp.is1220.MyFoodora;

public class Manager extends User {
	
	private static final long serialVersionUID = 2291793873364057722L;
	/**
	 * the MyFoodora core
	 */
	private MyFoodora myFoodora;
	
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
	

	public MyFoodora getMyFoodora() {
		return myFoodora;
	}

	public void setMyFoodora(MyFoodora myFoodora) {
		this.myFoodora = myFoodora;
	}
}
