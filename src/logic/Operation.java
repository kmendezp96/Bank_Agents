package logic;

 /*
 * @author Kevin Mendez
 * @version 2.0
 * This class contains the information related to one request of a client.
 *
 */

public class Operation {
	private String intention;
	private double amount;
	private String issue;

	/*
	*This constructor is used for resolve customer issue operation.
	* @param String with the intention
	* @param String with the issue specification
	 */
	public Operation(String intention, String issue) {
		this.intention = intention;
		this.amount = 0;
		this.issue = issue;
	}

	/*
	 *This constructor is used for withdrawals and deposits.
	 * @param String with the intention.
	 * @param double specifying the amount of the operation.
	 */
	public Operation(String intention, double amount) {
		this.intention = intention;
		this.amount = amount;
		this.issue = "";
	}
	/*
	* @return the String intention.
	 */
	public String getIntention() {
		return intention;
	}

	/*
	 * @return the double amount.
	 */
	public double getAmount() {
		return amount;
	}

	/*
	 * @return the String issue.
	 */
	public String getIssue() {
		return issue;
	}

}
