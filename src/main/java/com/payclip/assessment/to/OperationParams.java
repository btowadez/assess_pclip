package com.payclip.assessment.to;

public class OperationParams {
	
	private Long user_id;
	private String operation;
	private String transaction_id;
	private String transaction_json;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getTransaction_json() {
		return transaction_json;
	}
	public void setTransaction_json(String transaction_json) {
		this.transaction_json = transaction_json;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("***** OperationOptions Details *****\n");
		sb.append("user_id="+getUser_id()+"\n");
		sb.append("operation="+getOperation()+"\n");
		sb.append("transaction_id="+getTransaction_id()+"\n");
		sb.append("transaction_json="+getTransaction_json()+"\n");
		sb.append("*****************************");
		
		return sb.toString();
	}

}
