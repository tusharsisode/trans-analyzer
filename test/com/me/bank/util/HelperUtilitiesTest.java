/**
 * 
 */
package com.me.bank.util;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.me.bank.model.Transaction;

/**
 * @author Tushar Sisode
 *
 */
public class HelperUtilitiesTest {
	
	@Test
	public void testingParseToLocalDateTime() {
		LocalDateTime localDateTime = HelperUtilities
				.parseToLocalDateTime("20/10/2018 19:21:43");
		assertEquals("2018-10-20T19:21:43", localDateTime.toString());
	}
	
	@Test
	public void testingFormatDisplayAmount() {
		String formmattedAmount1 = HelperUtilities.formatDisplayAmount(-25);
		assertEquals("-$25.00", formmattedAmount1);
		
		String formmattedAmount2 = HelperUtilities.formatDisplayAmount(5.00);
		assertEquals("$5.00", formmattedAmount2);
	}
	
	@Ignore
	@Test
	public void testingParseInputCsvFile() {
		List<Transaction> transactions = null;
		try {
			transactions = HelperUtilities
					.parseInputCsvFile("D:\\transaction_records.csv");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Transaction mockedTransaction = new Transaction();
		mockedTransaction.setTransactionId("TX10001");
		mockedTransaction.setFromAccountId("ACC334455");
		mockedTransaction.setToAccountId("ACC778899");
		mockedTransaction.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("20/10/2018 12:47:55"));
		mockedTransaction.setAmount(Double.valueOf("25.00"));
		mockedTransaction.setTransactionType("PAYMENT");
		mockedTransaction.setRelatedTransaction(null);

		assertEquals(mockedTransaction, transactions.get(0));
	}
}
