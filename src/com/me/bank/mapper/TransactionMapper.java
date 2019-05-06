/**
 * 
 */
package com.me.bank.mapper;

import java.util.List;

import com.me.bank.model.Transaction;
import com.me.bank.util.HelperUtilities;

/**
 * This is the mapper class for mapping the parsed transaction records
 * to the Transaction model.
 * 
 * @author Tushar Sisode
 *
 */
public class TransactionMapper {

	/**
	 * This is the mapping method for transaction records
	 * @param transactionRecord
	 * @return
	 */
	public static Transaction 
	mapTransactionRecord(List<String> transactionRecord) {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(transactionRecord.get(0).trim());
		transaction.setFromAccountId(transactionRecord.get(1).trim());
		transaction.setToAccountId(transactionRecord.get(2).trim());
		transaction.setCreatedAt(HelperUtilities
				.parseToLocalDateTime(transactionRecord.get(3).trim()));
		transaction.setAmount(Double.valueOf(transactionRecord.get(4).trim()));
		transaction.setTransactionType(transactionRecord.get(5).trim());
		if (transactionRecord.get(5).trim().equals("REVERSAL")) {
			transaction.setRelatedTransaction(transactionRecord.get(6).trim());
		}
		return transaction;
	}
}
