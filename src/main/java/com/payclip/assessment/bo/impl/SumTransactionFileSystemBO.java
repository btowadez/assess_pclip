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
import com.payclip.assessment.bo.SumTransaction;
import com.payclip.assessment.to.SumTransactionsResult;
import com.payclip.assessment.to.Transaction;

@Service("sumTransactionBO")
public class SumTransactionFileSystemBO implements SumTransaction {
	
	private static final Logger logger = LoggerFactory.getLogger(ListTransactionFileSystemBO.class);
	
	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final ObjectMapper mapper = new ObjectMapper();
	
	private List<Transaction> transactions = new ArrayList<>();
	private Double sum = new Double(0);

	@Override
	public SumTransactionsResult sum(String user_id) {
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
				transactions.forEach(t-> {
					sum += t.getAmount();
				});
				SumTransactionsResult result = new SumTransactionsResult();
				result.setUser_id(user_id);
				result.setSum(sum);
				mapper.writeValue(out, result);
				byte[] data = out.toByteArray();
				logger.info(new String(data));
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			logger.error("user does not exist");
		}
		return null;
	}

}
