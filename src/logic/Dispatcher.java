package logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Dispatcher {
	LinkedList<Client> clients;

	ArrayList<Employee> employees;
	ArrayList<Cashier> cashiers;
	ArrayList<Supervisor> supervisors;
	ArrayList<Director> directors;
	ExecutorService service;
	public Dispatcher(LinkedList<Client> clients, ArrayList<Cashier> cashiers, ArrayList<Supervisor> supervisors,
			ArrayList<Director> directors,ArrayList<Employee>  employees) {
		this.clients = clients;
		this.cashiers = cashiers;
		this.supervisors = supervisors;
		this.directors = directors;

		this.employees = employees;
		this.service  = Executors.newFixedThreadPool(this.employees.size());
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
		Employee temp;
			if (this.employees.get(0).isAvailableStatus()) {
				temp = this.employees.remove(0);
				temp.setAvailableStatus(false);
				temp.setCurrentClient(firstClient);
				CompletableFuture
						.supplyAsync(temp, service)
						.thenAccept(response -> {
							System.out.println("El cliente " + response.getClient().getName() + " fue atendido en " + ((double) response.getClient().getAttentionTime() / 1000) + " segundos por " + response.getEmployee().getName());
						});
				temp.setAvailableStatus(true);
				this.employees.add(temp);
			}
				//System.out.println(this.cashiers.get(i).getCurrentClient().getName());
		//return temp;
	}



}
