package com.payclip.assessment.bo;

import com.payclip.assessment.to.SumTransactionsResult;

/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */
public interface SumTransaction {
	public SumTransactionsResult sum(String user_id);
}
