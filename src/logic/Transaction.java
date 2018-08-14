package logic;

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

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
