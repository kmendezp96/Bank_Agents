package logic;

/*
 * @author Kevin Mendez
 * @version 2.0
 * This class represent the transaction that includes one Client and one Employee
 * */
public class Transaction {
    private Client client;
    private Employee employee;

    public Transaction(Client client, Employee employee) {
        this.client = client;
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

}
