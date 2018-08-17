package logic;

import java.util.function.Supplier;

/*
 * @author Kevin Mendez
 * @version 2.0
 * This is an abstract class that contains all the commons atributes and methods of al the three kind of employees
 * it also implements the Supplier interface, so here is where the threads are implemented.
 * */
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

	public String getName() {
		return name;
	}

	public boolean isAvailableStatus() {
		return availableStatus;
	}

	public void setAvailableStatus(boolean availableStatus) {
		this.availableStatus = availableStatus;
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


	/*
	 * this method should update the money of a client making a deposit
	 * @param the amount of the deposit
	 * @return true if the deposit was done, false otherwise
	 * */
	public boolean makeADeposit(double amount){
		this.currentClient.setMoney(this.currentClient.getMoney() + amount);
		return true;
	}
	/*
	 * this method should update the money of a client making a withdrawal
	 * @param the amount of the withdrawal
	 * @return true if the withdrawal was done, false otherwise
	 * */
	public boolean makeAWithdrawal(double amount){
		if (this.currentClient.getMoney()<amount){
			return false;
		}
		else {
			this.currentClient.setMoney(this.currentClient.getMoney()-amount);
			return true;
		}
	}
	/*
	 * this method have a probability of 50% of solve a issue or not
	 * @param the amount of the withdrawal
	 * @return true if the issue was solved, false otherwise
	 * */
	public boolean resolveCustomerIssue(String issue){
		return Math.random() < 0.5;
	}

	/*
	 * this method execute the operation that the client needs
	 * @return the Client updated with the result of the operation
	 * */
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
	/*
	 * this is the Override get method for the threads implementation
	 * it return a transaction with this employee and the client after making an operation over him.
	 * */
	@Override
	public Transaction get(){
		Client tempClient;
		tempClient = this.makeOperation();
		return new Transaction(tempClient,this);

	}


}
