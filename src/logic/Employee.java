package logic;

import java.util.function.Supplier;

public abstract class Employee {
	
	private int id;
	private String name;
	private boolean availableStatus;
	
	public Employee(int id, String name, boolean availableStatus) {
		super();
		this.id = id;
		this.name = name;
		this.availableStatus = availableStatus;
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public boolean isAvailableStatus() {
		return availableStatus;
	}



	public void setAvailableStatus(boolean availableStatus) {
		this.availableStatus = availableStatus;
	}



	public boolean makeADeposit(double amount, Client client){	
		client.setMoney(client.getMoney() + amount);
		return true;
	}
	
	public boolean makeAWithdrawal(double amount, Client client){
		if (client.getMoney()<amount){
			return false;
		}
		else {
			client.setMoney(client.getMoney()-amount);
			return true;
		}
	}
	public boolean resolveCustomerIssue(String issue, Client client){
		return Math.random() < 0.5;
	}
	
}
