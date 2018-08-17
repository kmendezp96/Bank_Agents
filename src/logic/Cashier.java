package logic;


/*
 * @author Kevin Mendez
 * @version 2.0
 * This class represent a Cashier that extend the abstract class Employee
 * */
public class Cashier extends Employee {

	public Cashier(int id, String name, boolean availableStatus) {
		super(id, name, availableStatus);
		this.setLevel(1);
	}
}
