package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Supplier;

public class Dispatcher implements Supplier<Transaction> {
	LinkedList<Client> clients;
	
	ArrayList<Cashier> cashiers;
	ArrayList<Supervisor> supervisors;
	ArrayList<Director> directors;
	
	public Dispatcher(LinkedList<Client> clients, ArrayList<Cashier> cashiers, ArrayList<Supervisor> supervisors,
			ArrayList<Director> directors) {
		this.clients = clients;
		this.cashiers = cashiers;
		this.supervisors = supervisors;
		this.directors = directors;
	}

	public Transaction attend(){
		Client firstClient = this.clients.pop();
		Employee employee = new Cashier();
		boolean attended = false;
		try {
			if (!attended) {
				for (int i = 0; i < this.cashiers.size(); i++) {

					if (this.cashiers.get(i).isAvailableStatus()) {
						this.cashiers.get(i).setAvailableStatus(false);
						System.out.println("begi " + this.cashiers.get(i).getName() + " " + this.cashiers.get(i).isAvailableStatus());
						employee = this.cashiers.get(i);

						if (firstClient.getOperation().getIntention().equals("deposit")) {
							this.cashiers.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
							this.cashiers.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
							this.cashiers.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
							attended = true;
						}
						Thread.sleep(firstClient.getAttentionTime());
						System.out.println("final " + this.cashiers.get(i).getName() + " " + this.cashiers.get(i).isAvailableStatus());
						//this.cashiers.get(i).setAvailableStatus(true);
						break;
					}

				}
			}
			if (!attended) {
				for (int i = 0; i < this.supervisors.size(); i++) {

					if (this.supervisors.get(i).isAvailableStatus()) {
						this.supervisors.get(i).setAvailableStatus(false);
                        employee = this.cashiers.get(i);

						if (firstClient.getOperation().getIntention().equals("deposit")) {
							this.supervisors.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
							this.supervisors.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
							this.supervisors.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
							attended = true;
						}
						Thread.sleep(firstClient.getAttentionTime());
						//this.supervisors.get(i).setAvailableStatus(true);
						break;
					}

				}
			}
			if (!attended) {
				for (int i = 0; i < this.directors.size(); i++) {
					if (this.directors.get(i).isAvailableStatus()) {
						this.directors.get(i).setAvailableStatus(false);
                        employee = this.cashiers.get(i);
						Thread.sleep(firstClient.getAttentionTime());
						if (firstClient.getOperation().getIntention().equals("deposit")) {
							this.directors.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
							this.directors.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
							attended = true;

						} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
							this.directors.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
							attended = true;

						}
						Thread.sleep(firstClient.getAttentionTime());
						//this.directors.get(i).setAvailableStatus(true);
						break;
					}

				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	return new Transaction(firstClient, employee);

	}
	@Override
	public Transaction get(){

		return this.attend();

	}

	

}
