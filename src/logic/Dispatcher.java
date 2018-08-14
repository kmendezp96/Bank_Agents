package logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Dispatcher {
	LinkedList<Client> clients;
	
	ArrayList<Cashier> cashiers;
	ArrayList<Supervisor> supervisors;
	ArrayList<Director> directors;
	ExecutorService service;
	public Dispatcher(LinkedList<Client> clients, ArrayList<Cashier> cashiers, ArrayList<Supervisor> supervisors,
			ArrayList<Director> directors) {
		this.clients = clients;
		this.cashiers = cashiers;
		this.supervisors = supervisors;
		this.directors = directors;
		this.service  = Executors.newFixedThreadPool(this.directors.size() + this.supervisors.size() + this.cashiers.size());
	}

	public void attend(){

		try {
			for  (int i = 0;i<10;i++){
				Client firstClient = this.clients.pop();
				assign( firstClient, service);
			}
		}finally{
			if(service != null) service.shutdown();
		}
	}

	public void assign(Client firstClient, ExecutorService service){
		boolean attended = false;
		for (int i = 0; i < this.cashiers.size(); i++) {
			if (this.cashiers.get(i).isAvailableStatus()) {
				this.cashiers.get(i).setAvailableStatus(false);
				this.cashiers.get(i).setCurrentClient(firstClient);
				//System.out.println(this.cashiers.get(i).getCurrentClient().getName());
				attended = true;
				CompletableFuture
						.supplyAsync(this.cashiers.get(i), service)
						.thenAccept(response -> {
							System.out.println("El cliente "+ response.getName()+ " fue atendido en "+ ((double)response.getAttentionTime()/1000)+" segundos.");
						});
				this.cashiers.get(i).setAvailableStatus(true);
				break;
			}

		}
		if (!attended) {
			for (int i = 0; i < this.supervisors.size(); i++) {

				if (this.supervisors.get(i).isAvailableStatus()) {
					this.supervisors.get(i).setAvailableStatus(false);
					this.supervisors.get(i).setCurrentClient(firstClient);
					attended = true;
					CompletableFuture
							.supplyAsync(this.supervisors.get(i), service)
							.thenAccept(response -> {
								System.out.println("El cliente "+ response.getName()+ " fue atendido en "+ ((double)response.getAttentionTime()/1000)+" segundos.");
							});

					this.supervisors.get(i).setAvailableStatus(true);
					break;
				}

			}
		}
		if (!attended) {
			for (int i = 0; i < this.directors.size(); i++) {
				if (this.directors.get(i).isAvailableStatus()) {
					this.directors.get(i).setAvailableStatus(false);
					this.directors.get(i).setCurrentClient(firstClient);
					attended = true;
					CompletableFuture
							.supplyAsync(this.directors.get(i), service)
							.thenAccept(response -> {
								System.out.println("El cliente "+ response.getName()+ " fue atendido en "+ ((double)response.getAttentionTime()/1000)+" segundos.");
							});
					this.directors.get(i).setAvailableStatus(true);
					break;
				}

			}
		}
	}



}
