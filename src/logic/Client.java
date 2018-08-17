package logic;


/*
 * @author Kevin Mendez
 * @version 2.0
 * This class represent a Client into the system,
 * it use the Operation class
 * */
public class Client {
	private int id;
	private String name;
	private int attentionTime;
	private Operation operation;
	private double money;

	/*
	 * constructor of Client
	 * @param int with the id of the Client
	 * @param String with the name of the Client
	 * @param int with the attention time of the client
	 * @param Operation object with the information of the operation that the Client needs
	 * @param double of the money that the client have into his account
	 * */
	public Client(int id, String name, int attentionTime ,Operation operation, double money) {
		this.id = id;
		this.name = name;
		this.attentionTime = attentionTime;
		this.operation = operation;
		this.money = money;
	}
	/*
	* @return the name.
	* */
	public String getName() {
		return name;
	}
	/*
	 * @return the attention time.
	 * */
	public int getAttentionTime() {
		return attentionTime;
	}
	/*
	 * set the attention time
	 * @param attention time .
	 * */
	public void setAttentionTime(int attentionTime) {
		this.attentionTime = attentionTime;
	}
	/*
	 * @return the Operation.
	 * */
	public Operation getOperation() {
		return operation;
	}
	/*
	 * set the operation
	 * @param the operation
	 * */
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	/*
	 * @return the money
	 * */
	public double getMoney() {
		return money;
	}
	/*
	 * set the money
	 * @param new money
	 * */
	public void setMoney(double money) {
		this.money = money;
	}
	
	

}
