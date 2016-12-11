package Group9_Project_IS1220_part1_Colas_Prabakaran;


public abstract class User implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6262632605692759298L;
	protected String name ;
	protected int uniqueID ;
	protected String userName ;
	protected String password ;
	protected String userType ;
	protected static int lastID = 0 ;
	protected boolean activated = true ;
	protected MyFoodora myFoodora = null ;
	
	/**
	 * creates an user who will use the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 */
	public User(String name,String userName,String password, MyFoodora myFoodora) {
		uniqueID = lastID ;
		lastID++ ;
		this.name = name ;
		this.userName = userName ;
		this.password = password ;
		this.myFoodora = myFoodora ;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
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

	public MyFoodora getMyFoodora() {
		return myFoodora;
	}

	public void setMyFoodora(MyFoodora myFoodora) {
		this.myFoodora = myFoodora;
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
	
	
}
