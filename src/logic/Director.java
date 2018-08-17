package logic;

/*
 * @author Kevin Mendez
 * @version 2.0
 * This class represent a Director that extend the abstract class Employee
 * */
public class Director extends Employee {

	public Director(int id, String name, boolean availableStatus) {
		super(id, name, availableStatus);
		this.setLevel(3);
	}

}
