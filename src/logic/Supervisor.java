package logic;

public class Supervisor extends Employee{

	public Supervisor(int id, String name, boolean availableStatus) {
		super(id, name, availableStatus);
		this.setLevel(2);
	}

}
