package com.payclip.assessment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.payclip.assessment.bo.impl.TransactionOperationsBO;
import com.payclip.assessment.to.OperationParams;
import com.payclip.assessment.utils.Utils;

/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */

@SpringBootApplication
public class Application implements ApplicationRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	private TransactionOperationsBO transactionOperationsBO;
	
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Utils.validateArgs(args);
		
		OperationParams params = Utils.readOperationOptions(args);
        
		switch (params.getOperation()) {
			case Utils.ADD_OPERATION:
				transactionOperationsBO.add(params);
				break;
			case Utils.LIST_OPERATION:
				transactionOperationsBO.list(params.getUser_id(), params.getTransaction_id());
				break;
			case Utils.SUM_OPERATION:
				transactionOperationsBO.sum(params.getUser_id().toString());
				break;
			default:
				transactionOperationsBO.show(params.getUser_id(), params.getTransaction_id());
		}
		
	}
}
