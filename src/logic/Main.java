package logic;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Main {
	
	public static void main(String[] args){
		ExecutorService service = null;
		LinkedList<Client> clients = new LinkedList<>();
		
		ArrayList<Cashier> cashiers = new ArrayList<>();
		ArrayList<Supervisor> supervisors = new ArrayList<>();
		ArrayList<Director> directors = new ArrayList<>();
		Random random = new Random(System.currentTimeMillis());
		try{
			//Object Pool
			

			clients.add(new Client (1,"Andres Ramirez",  random.nextInt(5000) + 10000 , new Operation ("deposit", 30000), 4000));
			clients.add(new Client (2,"Felipe Rodriguez",random.nextInt(5000) + 10000,new Operation ("deposit", 2432), 4000));
			clients.add(new Client (3,"Antonio Castillo",random.nextInt(5000) + 10000,new Operation ("deposit", 4543), 564564));
			clients.add(new Client (4,"Daniel Ruiz",random.nextInt(5000) + 10000,new Operation ("deposit", 4322), 7000));
			clients.add(new Client (5,"Federico Valderrama",random.nextInt(5000) + 10000,new Operation ("deposit", 30000), 80000));
			clients.add(new Client (6,"Camilo Mendez",random.nextInt(5000) + 10000,new Operation ("deposit", 30000), 80000));
			clients.add(new Client (7,"Michael Martinez",random.nextInt(5000) + 10000,new Operation ("deposit", 30000), 80000));
			clients.add(new Client (8,"Luis Casas",random.nextInt(5000) + 10000,new Operation ("deposit", 30000), 80000));
			clients.add(new Client (9,"Raul Caicedo",random.nextInt(5000) + 10000,new Operation ("deposit", 30000), 80000));
			clients.add(new Client (10,"Kevin Hernandez",random.nextInt(5000) + 10000,new Operation ("deposit", 30000), 80000));

			cashiers.add(new Cashier(1,"Fabiola Hoyos", true));
			cashiers.add(new Cashier(2,"German Perez", true));
			cashiers.add(new Cashier(3,"Andrea Castillo", true));
			cashiers.add(new Cashier(4,"Rodrigo Gamboa", true));
			cashiers.add(new Cashier(5,"Tatiana hernandez", true));

			supervisors.add(new Supervisor(1,"Fabian Hoyos", true));
			supervisors.add(new Supervisor(2,"Fabio Perez", true));
			supervisors.add(new Supervisor(3,"Camilo Castillo", true));

			directors.add(new Director(4,"Rodrigo Ramirez", true));
			directors.add(new Director(5,"Felipe hernandez", true));
			service = Executors.newFixedThreadPool(directors.size() + supervisors.size() + cashiers.size());
			Dispatcher dispatcher = new Dispatcher(clients,cashiers, supervisors, directors);
			System.out.println("Abre el banco");
			for(int i = 0; i<10; i++){
				CompletableFuture
						.supplyAsync(dispatcher, service)
						.thenAccept(response -> {
							System.out.println("El cliente "+ response.getName()+ " fue atendido en "+ ((double)response.getAttentionTime()/1000)+" segundos.");
						});
			}
			
		}finally{
			if(service != null) service.shutdown();
		}
		//System.out.println(clients.size());
	}

}
