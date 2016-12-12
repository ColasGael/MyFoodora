package fr.ecp.is1220.MyFoodora;

public class Restaurant extends User {
	
	private static final long serialVersionUID = -983973017288067345L;
	private Position address ;
	private Menu menu ;
	
	/**
	 * creates a restaurant who will be used in the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 */
	public Restaurant(String name, String surname, String userName, String password) {
		super(name, "", userName, password);
		this.address = null ;
		this.menu = new Menu() ;
		
		this.setUserType ("restaurant") ;
	}
	
	/**
	 * creates a restaurant who will be used in the MyFoodora platform
	 * @param name : the name of the user
	 * @param userName : the user name in the MyFoodora platform
	 * @param password : the password of the user to login in the MyFoodora platform
	 * @param address : the address of the restaurant
	 */
	public Restaurant(String name, String userName, String password, Position address) {
		super(name, "", userName, password);
		this.address = address ;
		this.menu = new Menu() ;
		
		this.setUserType ("restaurant") ;
	}
	
	public Menu getMenu(){
		return menu ;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public void displayMenu(){
		System.out.println(this.menu.toString());
	}

	public Position getAddress() {
		return address;
	}

	public void setAddress(Position address) {
		this.address = address;
	}
	
	@Override
	public String toString(){
		return ("Name : " + this.getName() + "\n" + 
				"	Address " + address);
	}
}
