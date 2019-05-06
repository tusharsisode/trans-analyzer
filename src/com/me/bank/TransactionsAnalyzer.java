/**
 * 
 */
package com.me.bank;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.me.bank.engine.AnalysisEngine;
import com.me.bank.model.Transaction;
import com.me.bank.util.HelperUtilities;

/**
 * This is the main entry class where the system is initialized, 
 * the user inputs are accepted and the output is displayed
 * 
 * @author Tushar Sisode
 *
 */
public class TransactionsAnalyzer {

	private static List<Transaction> transactions = null;
	private static String inputAccountId = null;
	private static LocalDateTime parsedInputFromDate = null;
	private static LocalDateTime parsedInputToDate = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize the System
		System.out.println("\n");
		System.out.println("*********************************************");
		System.out.println("        ME BANK TRANSACTION ANALYSER         ");
		System.out.println("*********************************************");
		System.out.println("\n");
		
		// Initialize the scanner for accepting user inputs
		Scanner scanner = new Scanner(new InputStreamReader(System.in));

		// Accept the complete name and path of the CSV file containing
		// Transaction Records. Validate the entered path and proceed
		// only after the user enters correct file path.
		acceptFile(scanner);

		// Accept the Account ID
		acceptAccountId(scanner);

		// Accept the from date and validate if it is in correct format.
		// Proceed only after user enters the date in expected format.
		acceptFromDate(scanner);

		// Accept the To Date and validate if it is in correct format.
		// Proceed only after user enters the date in expected format.
		acceptToDate(scanner);

		// Close the scanner
		scanner.close();

		// Analyze the transactions for the input account ID 
		// and the date range.
		List<Double> creditOrDebitAmounts = AnalysisEngine
				.analyseTransactions(transactions, inputAccountId,
				parsedInputFromDate, parsedInputToDate);

		// Compute the relative balance for the account 
		// from creditOrDebitAmounts list
		double relativeBal = creditOrDebitAmounts.stream()
				.mapToDouble(d -> d).sum();
		
		// Display the final results
		System.out.println("*******************RESULTS*******************");
		System.out.println("Relative balance for the period is: " 
					+ HelperUtilities.formatDisplayAmount(relativeBal));
		System.out.println("Number of transactions included is: " 
					+ creditOrDebitAmounts.size());
		System.out.println("*********************************************");
	}

	/**
	 * This methods accepts the To Date from the cmd input terminal 
	 * and validates if it is in correct format. It proceeds only after 
	 * user enters the date in expected format.
	 * @param scanner
	 */
	private static void acceptToDate(Scanner scanner) {
		while (true) {
			System.out.println("Please enter To Date in " 
								+ "dd/MM/yyyy HH:mm:ss format: ");
			String inputToDate = scanner.nextLine();
			System.out.println("\n");
			try {
				parsedInputToDate = HelperUtilities
						.parseToLocalDateTime(inputToDate);
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Error!! Specified Date " 
									+ "is not in correct format.");
				System.out.println("\n");
				continue;
			}
		}
	}

	/**
	 * This methods accepts the From Date from the cmd input terminal 
	 * and validates if it is in correct format. It proceeds only after 
	 * user enters the date in expected format.
	 * @param scanner
	 */
	private static void acceptFromDate(Scanner scanner) {
		scanner.nextLine();
		while (true) {
			System.out.println("Please enter From Date in " 
							+ "dd/MM/yyyy HH:mm:ss format: ");
			String inputFromDate = scanner.nextLine();
			System.out.println("\n");
			try {
				parsedInputFromDate = HelperUtilities
						.parseToLocalDateTime(inputFromDate);
				break;
			} catch (DateTimeParseException e) {
				System.out.println("Error!! Specified Date " 
								+ "is not in correct format.");
				System.out.println("\n");
				continue;
			}
		}
	}

	/**
	 * This method accepts the Account ID from the cmd input terminal
	 * @param scanner
	 */
	private static void acceptAccountId(Scanner scanner) {
		System.out.println("Please enter Account ID: ");
		inputAccountId = scanner.next();
		System.out.println("\n");
	}

	/**
	 * This method accepts the complete name and path of the CSV file 
	 * containing transaction records from the cmd input terminal. 
	 * It validates the entered path and proceed 
	 * only after the user enters correct file path.
	 * @param scanner
	 */
	private static void acceptFile(Scanner scanner) {
		while (true) {
			System.out.println(
					"Please enter complete path and name of the" 
					+ " CSV file containing Transaction Records: ");
			System.out.println("For Example: D:\\transaction_records.csv");
			String inputCsvFilePath = scanner.next();
			System.out.println("\n");
			try {
				// Parse the input CSV file
				transactions = HelperUtilities
						.parseInputCsvFile(inputCsvFilePath);
				break;
			} catch (FileNotFoundException e) {
				System.out.println("Error!! Specified file " 
			                      + "or path was not found.");
				System.out.println("\n");
				continue;
			}
		}
	}
}
