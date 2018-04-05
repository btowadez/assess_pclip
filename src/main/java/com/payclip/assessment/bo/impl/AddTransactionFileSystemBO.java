package com.payclip.assessment.bo.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payclip.assessment.bo.AddTransaction;
import com.payclip.assessment.to.OperationParams;
import com.payclip.assessment.to.Transaction;
import com.payclip.assessment.utils.Utils;

@Service("addTransactionBO")
public class AddTransactionFileSystemBO implements AddTransaction {

	private static final Logger logger = LoggerFactory.getLogger(AddTransactionFileSystemBO.class);
	private ObjectMapper mapper = new ObjectMapper();

	public Transaction add(OperationParams params) {

		Transaction newTransaction = null;

		try {
			newTransaction = mapper.readValue(params.getTransaction_json(), Transaction.class);
			newTransaction.setTransaction_id(Utils.generateTransactionId());
			
			Path path = Paths.get(newTransaction.getUser_id().toString());
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
			mapper.writeValue(new File(path.getFileName() + "//" + newTransaction.getTransaction_id()),
					newTransaction);
			logger.info("Transaction: " + newTransaction.getTransaction_id() + " added.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		return newTransaction;

	}

}
