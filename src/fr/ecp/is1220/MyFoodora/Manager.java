package Group9_Project_IS1220_part1_Colas_Prabakaran;

public class Manager extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2291793873364057722L;
	private String surname ;
	
	/**
	 * creates a manager who will manage the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param surname : the surname of the user
	 */
	public Manager(String name, String userName, String password, MyFoodora myFoodora, String surname) {
		super(name, userName, password, myFoodora);
		this.surname = surname ;
	}
	
	/**
	 * adds the user user
	 * @param user : The user object 
	 */
	public void addUser(User user){
		
	}
	
	public void removeUser(User user){
		
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
