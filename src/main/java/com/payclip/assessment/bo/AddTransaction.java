package com.payclip.assessment.bo;

import com.payclip.assessment.to.OperationParams;
import com.payclip.assessment.to.Transaction;

/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */
public interface AddTransaction {
	
	public Transaction add(OperationParams params);

}
