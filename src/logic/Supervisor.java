package logic;
/*
 * @author Kevin Mendez
 * @version 2.0
 * This class represent a Supervisor that extend the abstract class Employee
 * */
public class Supervisor extends Employee{

	public Supervisor(int id, String name, boolean availableStatus) {
		super(id, name, availableStatus);
		this.setLevel(2);
	}

}
