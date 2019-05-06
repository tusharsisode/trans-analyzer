/**
 * 
 */
package com.me.bank.engine;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.me.bank.model.Transaction;

/**
 * This is the engine class where the transactions are analyzed
 * for the input account ID and the date range.
 * 
 * @author Tushar Sisode
 *
 */
public class AnalysisEngine {
	
	/**
	 * This is the core method that analyzes the transactions for the input
	 * account ID and the date range.
	 * @return
	 */
	public static List<Double> 
	analyseTransactions(List<Transaction> transactions, String inputAccountId,
			LocalDateTime parsedInputFromDate, LocalDateTime parsedInputToDate) {
		// Filter the list of transactions for the input account ID 
		// in either debit or credit and where the transactionType is PAYMENT
		List<Transaction> transForInputAcc = transactions.stream()
				.filter((t) -> (inputAccountId.equals(t.getFromAccountId()) 
						|| inputAccountId.equals(t.getToAccountId()))
								&& t.getTransactionType().equals("PAYMENT"))
				.collect(Collectors.toList());
		
		// Filter the above list of transForInputAcc for the 
		// given From and To dates
		List<Transaction> transForGivenDates = transForInputAcc.stream()
				.filter((t) -> (t.getCreatedAt().isEqual(parsedInputFromDate)
						|| t.getCreatedAt().isAfter(parsedInputFromDate))
						&& (t.getCreatedAt().isEqual(parsedInputToDate)
						|| t.getCreatedAt().isBefore(parsedInputToDate)))
				.collect(Collectors.toList());

		// Filter out the Reversed transaction IDs from the 
		// main transactions list
		List<String> reversedTransIds = transactions.stream()
				.filter((t) -> t.getTransactionType().equals("REVERSAL"))
				.map(Transaction::getRelatedTransaction)
				.collect(Collectors.toList());

		// Getting rid of the transactions that are reversed 
		// in transForGivenDates list
		List<Transaction> transForGivenDatesWOR = transForGivenDates.stream()
				.filter((t) -> !reversedTransIds.contains(t.getTransactionId()))
				.collect(Collectors.toList());
		
		// Populate creditOrDebitAmounts list with debit or credit amounts
		List<Double> creditOrDebitAmounts = new ArrayList<Double>();
		transForGivenDatesWOR.forEach(tra -> {
			// If the input account ID is equal to fromAccountId, 
			// that means the amount is debited from the account,
			// and hence should be stored as negative
			if (inputAccountId.equals(tra.getFromAccountId())) {
				creditOrDebitAmounts.add(-tra.getAmount());
			}
			// If the input account ID is equal to toAccountId, 
			// that means the amount is credited in the account,
			// and hence should be stored as positive
			if (inputAccountId.equals(tra.getToAccountId())) {
				creditOrDebitAmounts.add(tra.getAmount());
			}
		});
		return creditOrDebitAmounts;
	}

}
