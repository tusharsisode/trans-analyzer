/**
 * 
 */
package com.me.bank.engine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.me.bank.model.Transaction;
import com.me.bank.util.HelperUtilities;

/**
 * @author Tushar Sisode
 *
 */
public class AnalysisEngineTest {
	
	@Test
	public void testingAnalyseTransactions() {
		List<Double> validRelativeTransactionAmounts1 = AnalysisEngine
				.analyseTransactions(getMockedTransactions()
						, "ACC334455"
						, HelperUtilities
						.parseToLocalDateTime("20/10/2018 12:00:00")
						, HelperUtilities
						.parseToLocalDateTime("20/10/2018 19:00:00"));
		List<Double> expectedList1 = Arrays.asList(-25.0);
		assertEquals(expectedList1, validRelativeTransactionAmounts1);
		
		List<Double> validRelativeTransactionAmounts2 = AnalysisEngine
				.analyseTransactions(getMockedTransactions()
						, "ACC778899"
						, HelperUtilities
						.parseToLocalDateTime("20/10/2018 12:00:00")
						, HelperUtilities
						.parseToLocalDateTime("20/10/2018 18:20:00"));
		List<Double> expectedList2 = Arrays.asList(25.0, 5.0);
		assertEquals(expectedList2, validRelativeTransactionAmounts2);
		
		List<Double> validRelativeTransactionAmounts3 = AnalysisEngine
				.analyseTransactions(getMockedTransactions()
						, "ACC998877"
						, HelperUtilities
						.parseToLocalDateTime("20/10/2018 12:30:00")
						, HelperUtilities
						.parseToLocalDateTime("20/10/2018 18:20:25"));
		List<Double> expectedList3 = Arrays.asList(-5.0);
		assertEquals(expectedList3, validRelativeTransactionAmounts3);
	}
	
	private List<Transaction> getMockedTransactions() {
		List<Transaction> mockedTransactions = new ArrayList<Transaction>();
		
		Transaction mockedTransaction1 = new Transaction();
		mockedTransaction1.setTransactionId("TX10001");
		mockedTransaction1.setFromAccountId("ACC334455");
		mockedTransaction1.setToAccountId("ACC778899");
		mockedTransaction1.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("20/10/2018 12:47:55"));
		mockedTransaction1.setAmount(Double.valueOf("25.00"));
		mockedTransaction1.setTransactionType("PAYMENT");
		mockedTransaction1.setRelatedTransaction(null);
		mockedTransactions.add(mockedTransaction1);
		
		Transaction mockedTransaction2 = new Transaction();
		mockedTransaction2.setTransactionId("TX10002");
		mockedTransaction2.setFromAccountId("ACC334455");
		mockedTransaction2.setToAccountId("ACC998877");
		mockedTransaction2.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("20/10/2018 17:33:43"));
		mockedTransaction2.setAmount(Double.valueOf("10.50"));
		mockedTransaction2.setTransactionType("PAYMENT");
		mockedTransaction2.setRelatedTransaction(null);
		mockedTransactions.add(mockedTransaction2);
		
		Transaction mockedTransaction3 = new Transaction();
		mockedTransaction3.setTransactionId("TX10003");
		mockedTransaction3.setFromAccountId("ACC998877");
		mockedTransaction3.setToAccountId("ACC778899");
		mockedTransaction3.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("20/10/2018 18:00:00"));
		mockedTransaction3.setAmount(Double.valueOf("5.00"));
		mockedTransaction3.setTransactionType("PAYMENT");
		mockedTransaction3.setRelatedTransaction(null);
		mockedTransactions.add(mockedTransaction3);
		
		Transaction mockedTransaction4 = new Transaction();
		mockedTransaction4.setTransactionId("TX10004");
		mockedTransaction4.setFromAccountId("ACC334455");
		mockedTransaction4.setToAccountId("ACC998877");
		mockedTransaction4.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("20/10/2018 19:45:00"));
		mockedTransaction4.setAmount(Double.valueOf("10.50"));
		mockedTransaction4.setTransactionType("REVERSAL");
		mockedTransaction4.setRelatedTransaction("TX10002");
		mockedTransactions.add(mockedTransaction4);
		
		Transaction mockedTransaction5 = new Transaction();
		mockedTransaction5.setTransactionId("TX10005");
		mockedTransaction5.setFromAccountId("ACC334455");
		mockedTransaction5.setToAccountId("ACC778899");
		mockedTransaction5.setCreatedAt(HelperUtilities
				.parseToLocalDateTime("21/10/2018 09:30:00"));
		mockedTransaction5.setAmount(Double.valueOf("7.25"));
		mockedTransaction5.setTransactionType("PAYMENT");
		mockedTransaction5.setRelatedTransaction(null);
		mockedTransactions.add(mockedTransaction5);
		
		return mockedTransactions;
	}
	
}
