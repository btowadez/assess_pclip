package com.payclip.assessment.utils;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;

import com.payclip.assessment.to.OperationParams;

/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */
public class Utils {
	
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	
	public static final String ADD_OPERATION = "add";
	public static final String LIST_OPERATION = "list";
	public static final String SUM_OPERATION = "sum";
	
	private static boolean isValidOperation(String operation) {
		return operation.toLowerCase().equals(ADD_OPERATION) || operation.toLowerCase().equals(LIST_OPERATION) 
				||operation.toLowerCase().equals(SUM_OPERATION);
	}
	
	public static void validateArgs(ApplicationArguments args) {
		logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
		if(args.getNonOptionArgs()==null || args.getNonOptionArgs().size()==0 
				|| args.getNonOptionArgs().size()<2 || args.getNonOptionArgs().size()>3) {
			logger.info("Please execute the program as follows:");
			logger.info("java -jar application-1.0.0.jar <user_id> add <transaction_json> OR");
			logger.info("java -jar application-1.0.0.jar <user_id> <transaction_id> OR");
			logger.info("java -jar application-1.0.0.jar <user_id> list OR");
			logger.info("java -jar application-1.0.0.jar <user_id> sum \n");
			System.exit(0);
		}
		//first parameter must be a number
		try {
			Long.parseLong(args.getNonOptionArgs().get(0));
		} catch(NumberFormatException nfe) {
			logger.info("First parameter must be a number.");
		}
		
	}
	
	public static OperationParams readOperationOptions(ApplicationArguments args) {
		OperationParams appOperation = new OperationParams();
        
        for (int i = 0; i < args.getNonOptionArgs().size(); i++) {
        	String argument = args.getNonOptionArgs().get(i);
        	
        	switch (i) {
			case 0:
				appOperation.setUser_id(Long.parseLong(argument));
				break;
			case 1:
				if(isValidOperation(argument))
					appOperation.setOperation(argument);
				else {
					if(args.getNonOptionArgs().size()==2) {
						appOperation.setTransaction_id(argument);
						appOperation.setOperation("show");
					} else{
						logger.error("Operation not supported");
						System.exit(1);
					}
				}
				break;
			case 2:
				appOperation.setTransaction_json(argument);
			default:
				break;
			}
		}
        
        return appOperation;
	}
	
	public static String generateTransactionId() {
		Long longKey = System.currentTimeMillis();
		longKey++;
		return longKey.toString();
	}

}
