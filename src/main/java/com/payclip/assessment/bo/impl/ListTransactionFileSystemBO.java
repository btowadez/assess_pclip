package com.payclip.assessment.bo.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payclip.assessment.bo.ListTransaction;
import com.payclip.assessment.to.Transaction;

@Service("listTransactionBO")
public class ListTransactionFileSystemBO implements ListTransaction {
	
	private static final Logger logger = LoggerFactory.getLogger(ListTransactionFileSystemBO.class);
	
	private List<Transaction> transactions = new ArrayList<>();
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void list(Long user_id, String transaction_id) {
		Path path = Paths.get(user_id.toString());
		if (Files.exists(path)) {
			try {
				Files.walk(path)
				 .filter(Files::isRegularFile)
				 .forEach(f->{
					 try (Stream<String> stream = Files.lines(Paths.get(f.toString()))) {
						 stream.forEach(line-> {
							 try {
								transactions.add(mapper.readValue(line, Transaction.class));
							} catch (IOException e) {
								e.printStackTrace();
							}
						 });
					 } catch (IOException e) {
							e.printStackTrace();
					 }
				 });
				mapper.writeValue(out, transactions);
				byte[] data = out.toByteArray();
				logger.info(new String(data));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			logger.error("user does not exist");
		}
	}

}
