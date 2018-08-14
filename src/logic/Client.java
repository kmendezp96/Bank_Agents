package logic;

public class Client {
	private int id;
	private String name;
	private int attentionTime;
	private Operation operation;
	private double money;
	
	public Client(int id, String name, int attentionTime ,Operation operation, double money) {
		//Random aleatorio = new Random(System.currentTimeMillis());
		this.id = id;
		this.name = name;
		this.attentionTime = attentionTime;
		this.operation = operation;
		this.money = money;
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

	public int getAttentionTime() {
		return attentionTime;
	}

	public void setAttentionTime(int attentionTime) {
		this.attentionTime = attentionTime;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	

}
