package logic;

import java.util.function.Supplier;

public abstract class Employee implements Supplier<Client> {
	
	private int id;
	private String name;
	private boolean availableStatus;
	private Client currentClient;
	
	public Employee(int id, String name, boolean availableStatus) {
		super();
		this.id = id;
		this.name = name;
		this.availableStatus = availableStatus;
		this.currentClient = null;
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

	public Client makeOp(Client firstClient) {
		try {
			//System.out.println(firstClient.getName());

			Thread.sleep(firstClient.getAttentionTime());
			if (firstClient.getOperation().getIntention().equals("deposit")) {
				this.makeADeposit(firstClient.getOperation().getAmount(), firstClient);
			} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
				this.makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
			} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
				this.resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
			}


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return firstClient;
	}

	@Override
	public Client get(){
		//aqui cambia el valor de currentClient
		Client tempClient;
		System.out.println(this.currentClient.getName());
		tempClient = this.makeOp(this.currentClient);

		return tempClient;

	}
}
