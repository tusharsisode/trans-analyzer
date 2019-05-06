/**
 * 
 */
package com.me.bank.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.me.bank.model.Transaction;
import com.me.bank.util.HelperUtilities;

/**
 * @author Tushar Sisode
 *
 */
public class TransactionMapperTest {
	
	@Test
	public void testingMapTransactionRecord() {
		List<String> transactionRecord = Arrays.asList("TX10001", "ACC334455",
				"ACC778899", "20/10/2018 12:47:55","25.00","PAYMENT");
		Transaction mappedTransaction = TransactionMapper
				.mapTransactionRecord(transactionRecord);
		
		Transaction mockedTransaction = new Transaction();
		mockedTransaction.setTransactionId("TX10001");
		mockedTransaction.setFromAccountId("ACC334455");
		mockedTransaction.setToAccountId("ACC778899");
		mockedTransaction.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("20/10/2018 12:47:55"));
		mockedTransaction.setAmount(Double.valueOf("25.00"));
		mockedTransaction.setTransactionType("PAYMENT");
		mockedTransaction.setRelatedTransaction(null);

		assertEquals(mockedTransaction, mappedTransaction);
	}
}
