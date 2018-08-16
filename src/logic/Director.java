package logic;

public class Director extends Employee {

	public Director(int id, String name, boolean availableStatus) {
		super(id, name, availableStatus);
		this.setLevel(3);
	}

}
