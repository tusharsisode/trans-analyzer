/**
 * 
 */
package com.me.bank.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.me.bank.mapper.TransactionMapper;
import com.me.bank.model.Transaction;

/**
 * This is the utility class containing all the utility
 * methods required for the application.
 * 
 * @author Tushar Sisode
 *
 */
public class HelperUtilities {
	
	/**
	 * This method parses input csv file and returns a 
	 * list of transactions.
	 * @param inputCsvFilePath
	 * @throws FileNotFoundException
	 */
	public static List<Transaction> parseInputCsvFile(String inputCsvFilePath) 
			throws FileNotFoundException {
		List<Transaction> transactions = new ArrayList<Transaction>();

		// Initialize the scanner with the transactions input csv file
		Scanner fileScanner = new Scanner(new File(inputCsvFilePath));

		// Ignore the header record in the file
		fileScanner.nextLine();

		// Parse the file and store the parsed transaction record in
		// transactions ArrayList
		while (fileScanner.hasNextLine()) {
			List<String> transactionRecord = Stream.of(fileScanner.nextLine())
					.map(w -> w.split(","))
					.flatMap(Arrays::stream).collect(Collectors.toList());
			transactions
			.add(TransactionMapper.mapTransactionRecord(transactionRecord));
		}

		// Close the scanner
		fileScanner.close();
		
		return transactions;
	}
	
	/**
	 * This method parses the string date time to return a LocalDateTime object.
	 * @param dateTime
	 * @return
	 */
	public static LocalDateTime parseToLocalDateTime(String dateTime) 
			throws DateTimeParseException {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime
				.parse(dateTime, formatter);
		return localDateTime;
	}
	
	/**
	 * This method formats the dollar amount to display.
	 * @param relativeBal
	 * @return
	 */
	public static String formatDisplayAmount (double relativeBal) {
		DecimalFormat decimalFormat = new DecimalFormat("$#.00;-$#.00");
		return decimalFormat.format(relativeBal);
	}
}
