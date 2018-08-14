package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Supplier;

public class Dispatcher implements Supplier<Client> {
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

	public Client attend(){
		//el tiempo de respuesta sera haciendo sleep al codigo
		//collable y los supplier
		/*
		* Aqui deberia ir el for
		*
		* */
		Client firstClient = this.clients.pop();
		boolean attended = false;
		try {

			for (int i = 0; i < this.cashiers.size(); i++) {
				if (this.cashiers.get(i).isAvailableStatus()) {
					this.cashiers.get(i).setAvailableStatus(false);
					Thread.sleep(firstClient.getAttentionTime());
					if (firstClient.getOperation().getIntention().equals("deposit")) {
						this.cashiers.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient);
						attended = true;
						/*if (this.cashiers.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient))
							System.out.println("The "+ firstClient.getName() +" deposit for "+ firstClient.getOperation().getAmount() + " dolars was made successfully");
						else
							System.out.println("The "+ firstClient.getName() +" deposit for "+ firstClient.getOperation().getAmount() + " could not be done");*/
					} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
						this.cashiers.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
						attended = true;
						/*if(this.cashiers.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient))
							System.out.println("The "+ firstClient.getName() +" withdrawal for "+ firstClient.getOperation().getAmount() + " dolars was made successfully");
						else
							System.out.println("The "+ firstClient.getName() +" withdrawal for "+ firstClient.getOperation().getAmount() + " could not be done");*/
					} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
						this.cashiers.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
						attended = true;
						/*if(this.cashiers.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient))
							System.out.println("The "+ firstClient.getName() +" issue was solved successfully");
						else
							System.out.println("The "+ firstClient.getName() +" issue could not be solved");*/
					}

					this.cashiers.get(i).setAvailableStatus(true);
					break;
				}

			}
			if (!attended) {
				for (int i = 0; i < this.supervisors.size(); i++) {

					if (this.supervisors.get(i).isAvailableStatus()) {
						this.supervisors.get(i).setAvailableStatus(false);
						Thread.sleep(firstClient.getAttentionTime());
						if (firstClient.getOperation().getIntention().equals("deposit")) {
							//System.out.println("Por un supervisor");
							this.supervisors.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						/*if (this.cashiers.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient))
							System.out.println("The "+ firstClient.getName() +" deposit for "+ firstClient.getOperation().getAmount() + " dolars was made successfully");
						else
							System.out.println("The "+ firstClient.getName() +" deposit for "+ firstClient.getOperation().getAmount() + " could not be done");*/
						} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
							this.supervisors.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
						/*if(this.cashiers.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient))
							System.out.println("The "+ firstClient.getName() +" withdrawal for "+ firstClient.getOperation().getAmount() + " dolars was made successfully");
						else
							System.out.println("The "+ firstClient.getName() +" withdrawal for "+ firstClient.getOperation().getAmount() + " could not be done");*/
						} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
							this.supervisors.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
							attended = true;
						/*if(this.cashiers.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient))
							System.out.println("The "+ firstClient.getName() +" issue was solved successfully");
						else
							System.out.println("The "+ firstClient.getName() +" issue could not be solved");*/
						}

						this.supervisors.get(i).setAvailableStatus(true);
						break;
					}

				}
			}
			if (!attended) {
				for (int i = 0; i < this.directors.size(); i++) {
					if (this.directors.get(i).isAvailableStatus()) {
						this.directors.get(i).setAvailableStatus(false);
						Thread.sleep(firstClient.getAttentionTime());
						if (firstClient.getOperation().getIntention().equals("deposit")) {
							this.directors.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient);
							//System.out.println("Por un director");
							attended = true;
					/*if (this.cashiers.get(i).makeADeposit(firstClient.getOperation().getAmount(), firstClient))
						System.out.println("The "+ firstClient.getName() +" deposit for "+ firstClient.getOperation().getAmount() + " dolars was made successfully");
					else
						System.out.println("The "+ firstClient.getName() +" deposit for "+ firstClient.getOperation().getAmount() + " could not be done");*/
						} else if (firstClient.getOperation().getIntention().equals("withdrawal")) {
							this.directors.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient);
							attended = true;
					/*if(this.cashiers.get(i).makeAWithdrawal(firstClient.getOperation().getAmount(), firstClient))
						System.out.println("The "+ firstClient.getName() +" withdrawal for "+ firstClient.getOperation().getAmount() + " dolars was made successfully");
					else
						System.out.println("The "+ firstClient.getName() +" withdrawal for "+ firstClient.getOperation().getAmount() + " could not be done");*/
						} else if (firstClient.getOperation().getIntention().equals("resolve customer issue")) {
							this.directors.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient);
							attended = true;
					/*if(this.cashiers.get(i).resolveCustomerIssue(firstClient.getOperation().getIssue(), firstClient))
						System.out.println("The "+ firstClient.getName() +" issue was solved successfully");
					else
						System.out.println("The "+ firstClient.getName() +" issue could not be solved");*/
						}

						this.directors.get(i).setAvailableStatus(true);
						break;
					}

				}
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	return firstClient;

	}
	@Override
	public Client get(){
		//preguntar random siempre igual
		int AttentionTime=0;
		Client tempClient;
		tempClient = this.attend();
		AttentionTime = tempClient.getAttentionTime();

		return tempClient;

	}
	/*public Integer get(){
		//preguntar random siempre igual
		int AttentionTime=0;
		Client tempClient;
		try {
			tempClient = this.attend();
			Thread.sleep(tempClient.getAttentionTime());
			AttentionTime = tempClient.getAttentionTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return AttentionTime;

	}*/
	

}
