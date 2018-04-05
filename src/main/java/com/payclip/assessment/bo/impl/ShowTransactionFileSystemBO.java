package com.payclip.assessment.bo.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.payclip.assessment.bo.ShowTransaction;
/**
 * 
 * @author betowadez@gmail.com Alberto Juarez
 *
 */
@Service("showTransactionBO")
public class ShowTransactionFileSystemBO implements ShowTransaction {
	
	private static final Logger logger = LoggerFactory.getLogger(ShowTransactionFileSystemBO.class);

	@Override
	public void show(Long user_id, String transaction_id) {
		
		Path path = Paths.get(user_id.toString());
		if (Files.exists(path)) {
			try {
				Files.walk(path)
				 .filter(Files::isRegularFile)
				 .forEach(f->{
					 if(f.toString().equals(user_id+"/"+transaction_id)) {
						 try (Stream<String> stream = Files.lines(Paths.get(f.toString()))) {
							 stream.forEach(System.out::println);
						 } catch (IOException e) {
								e.printStackTrace();
						 }
					 }
				 });
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logger.error("Transaction not found");
		}
		
	}

}
