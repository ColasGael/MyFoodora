package fr.ecp.is1220.MyFoodora;


public abstract class User implements java.io.Serializable {
	
	private static final long serialVersionUID = 6262632605692759298L;
	private String name ;
	private String surname ;
	private int uniqueID ;
	private String userName ;
	private String password ;
	/**
	 * the type of user : manager, customer, courier or restaurant
	 */
	private String userType ;
	/**
	 * the static lastID ensure that the IDs of all users are different
	 */
	protected static int lastID = 0 ;
	/**
	 * true if the account of the user is activated
	 */
	private boolean activated = false ;
	
	/**
	 * creates an user who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param surname : the surname of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 */
	public User(String name, String surname, String userName, String password) {
		uniqueID = lastID ;
		lastID++ ;
		this.name = name ;
		this.surname = surname;
		this.userName = userName ;
		this.password = password ;
		this.activated = true ;
	}
	
	/**
	 * unregister the user from the system
	 * @param myFoodora : myFoodora core
	 */
	public void unregister(MyFoodora myFoodora){
		myFoodora.removeUser(uniqueID);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public static int getLastID() {
		return lastID;
	}

	public static void setLastID(int lastID) {
		User.lastID = lastID;
	}

	@Override
	public String toString(){
		return (this.userType + " ID n°" + this.uniqueID + " " + name + " " + surname + " username:"+userName+"\n");
	}
	
	@Override
	public boolean equals (Object o){
		boolean isequal = false;
		if (o instanceof User){
			User user = (User)o;
			isequal = (this.uniqueID == user.getUniqueID());
		}
		return isequal;
	}
	
}
