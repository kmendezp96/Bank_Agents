package logic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/*
 * @author Kevin Mendez
 * @version 2.0
 * The Dispatcher class must assign the clients to the employees, it have the list of clients, emplooyes and the Executor Service
 * */

public class Dispatcher {
	private LinkedList<Client> clients;

	private List<Employee> employees;
	private LinkedList<Client> waitingClients;
	private ExecutorService service;
	public Dispatcher(LinkedList<Client> clients, ArrayList<Employee>  employees) {
		this.clients = clients;
		this.employees = employees;
		this.service  = Executors.newFixedThreadPool(this.employees.size());
		this.waitingClients = new LinkedList<>();
	}

	/*
	 * this method attend all the clients
	 */
	public void attend(){
		while (clients.size()>employees.size()){
				waitingClients.add(clients.pop());
		}

		try {
			while(clients.size()>0 ){
				Client firstClient = this.clients.pop();
				assign( firstClient);
			}

		}finally{
			if(service != null) service.shutdown();

		}

	}

	/*
	 * this method assign the client to the corresponding employee
	 * @param the client to be assigned
	 */
	public void assign(Client firstClient){
		Employee temp;
		if (this.employees.get(0).isAvailableStatus()) {
			temp = this.employees.remove(0);
			temp.setAvailableStatus(false);
			temp.setCurrentClient(firstClient);
			CompletableFuture
					.supplyAsync(temp, this.service)
					.thenAccept(response -> {
						System.out.println("El cliente " + response.getClient().getName() + " fue atendido en " + ((double) response.getClient().getAttentionTime() / 1000) + " segundos por " + response.getEmployee().getName());
						temp.setAvailableStatus(true);
						//meter cosas aqui
						this.employees.add(temp);
						this.employees = this.order();
						this.clients.add(this.waitingClients.remove(0));
					});



		}
	}
	/*
	 * this method order the employees list in Cashiers first, the the supervisors and then the directors
	 */
	public List<Employee> order(){
		return employees.stream()
				.sorted(Comparator.comparingInt(Employee::getLevel))
				.collect(Collectors.<Employee>toList());
	}

}
