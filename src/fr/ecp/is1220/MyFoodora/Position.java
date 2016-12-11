package fr.ecp.is1220.MyFoodora;

public class Position implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8165237176281387856L;
	double x ;
	double y ;
	
	/**
	 * creates a position defined by two coordinates
	 * @param x
	 * @param y
	 */
	public Position(double x,double y) {
		this.x = x ;
		this.y = y ;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
