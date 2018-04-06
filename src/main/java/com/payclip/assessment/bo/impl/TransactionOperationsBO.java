package com.payclip.assessment.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payclip.assessment.bo.AddTransaction;
import com.payclip.assessment.bo.ListTransaction;
import com.payclip.assessment.bo.ShowTransaction;
import com.payclip.assessment.bo.SumTransaction;
import com.payclip.assessment.to.SumTransactionsResult;
import com.payclip.assessment.to.OperationParams;
import com.payclip.assessment.to.Transaction;

@Service("transOpsExecutor")
public class TransactionOperationsBO implements AddTransaction, ShowTransaction, ListTransaction, SumTransaction {
	
	@Autowired
	AddTransaction addTransactionBO;
	
	@Autowired
	ShowTransaction showTransactionBO;
	
	@Autowired
	ListTransaction listTransactionBO;
	
	@Autowired
	SumTransaction sumTransactionBO;

	@Override
	public Transaction add(String user_id, String transactionJson) {
		return addTransactionBO.add(user_id, transactionJson);
	}

	@Override
	public void show(Long user_id, String transaction_id) {
		showTransactionBO.show(user_id, transaction_id);
	}

	@Override
	public void list(Long user_id, String transaction_id) {
		listTransactionBO.list(user_id, transaction_id);
	}

	@Override
	public SumTransactionsResult sum(String user_id) {
		return sumTransactionBO.sum(user_id);
	}
	
}
