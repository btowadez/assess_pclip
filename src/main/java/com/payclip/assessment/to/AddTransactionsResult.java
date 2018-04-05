package com.payclip.assessment.to;

/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */
public class AddTransactionsResult {
	
	private String user_id;
	private Double sum;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("***** AddTransactionsResult Details *****\n");
		sb.append("user_id="+getUser_id()+"\n");
		sb.append("sum="+getSum()+"\n");
		sb.append("*****************************");
		
		return sb.toString();
	}

}
