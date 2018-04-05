package com.payclip.assessment.bo;

import com.payclip.assessment.to.OperationParams;
import com.payclip.assessment.to.Transaction;

public interface AddTransaction {
	
	public Transaction add(OperationParams params);

}
