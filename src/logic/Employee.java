package logic;

import java.util.function.Supplier;

public abstract class Employee implements Supplier<Transaction> {
	
	private int id;
	private String name;
	private boolean availableStatus;
	private Client currentClient;
	private int level;
	
	public Employee(int id, String name, boolean availableStatus) {
		this.id = id;
		this.name = name;
		this.availableStatus = availableStatus;
		this.currentClient = null;
		this.level = 0;
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

	public Client getCurrentClient() {
		return currentClient;
	}

	public void setCurrentClient(Client currentClient) {

		this.currentClient = currentClient;
		//System.out.println(this.currentClient.getName());
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean makeADeposit(double amount){
		this.currentClient.setMoney(this.currentClient.getMoney() + amount);
		return true;
	}
	
	public boolean makeAWithdrawal(double amount){
		if (this.currentClient.getMoney()<amount){
			return false;
		}
		else {
			this.currentClient.setMoney(this.currentClient.getMoney()-amount);
			return true;
		}
	}
	public boolean resolveCustomerIssue(String issue){
		return Math.random() < 0.5;
	}

	public Client makeOperation() {
		try {
			Thread.sleep(this.currentClient.getAttentionTime());
			if (this.currentClient.getOperation().getIntention().equals("deposit")) {
				this.makeADeposit(this.currentClient.getOperation().getAmount());
			} else if (this.currentClient.getOperation().getIntention().equals("withdrawal")) {
				this.makeAWithdrawal(this.currentClient.getOperation().getAmount());
			} else if (this.currentClient.getOperation().getIntention().equals("resolve customer issue")) {
				this.resolveCustomerIssue(this.currentClient.getOperation().getIssue());
			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.currentClient;
	}

	@Override
	public Transaction get(){
		Client tempClient;
		tempClient = this.makeOperation();
		return new Transaction(tempClient,this);

	}


}
