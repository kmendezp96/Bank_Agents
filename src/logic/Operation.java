package logic;

public class Operation {
	private String intention;
	private double amount;
	private String issue;
	public Operation(String intention, String issue) {
		this.intention = intention;
		this.amount = 0;
		this.issue = issue;
	}
	
	public Operation(String intention, double amount) {
		this.intention = intention;
		this.amount = amount;
		this.issue = "";
	}

	public String getIntention() {
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}
	
	
	
	
	

}
