package com.payclip.assessment.to;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */
public class Transaction {
	
	private String transaction_id;
	private Double amount;
	private String description;
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	private Long user_id;

	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("***** Transaction Details *****\n");
		sb.append("transaction_id="+getTransaction_id()+"\n");
		sb.append("amount="+getAmount()+"\n");
		sb.append("description="+getDescription()+"\n");
		sb.append("date="+getDate()+"\n");
		sb.append("user_id="+getUser_id()+"\n");
		sb.append("*****************************");
		
		return sb.toString();
	}
	
}
