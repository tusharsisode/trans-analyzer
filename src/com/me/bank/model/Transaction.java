/**
 * 
 */
package com.me.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This is the model class representing a Transaction record.
 * 
 * @author Tushar Sisode
 *
 */
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionId;
	private String fromAccountId;
	private String toAccountId;
	private LocalDateTime createdAt;
	private Double amount;
	private String transactionType;
	private String relatedTransaction;
	
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the fromAccountId
	 */
	public String getFromAccountId() {
		return fromAccountId;
	}
	/**
	 * @param fromAccountId the fromAccountId to set
	 */
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	/**
	 * @return the toAccountId
	 */
	public String getToAccountId() {
		return toAccountId;
	}
	/**
	 * @param toAccountId the toAccountId to set
	 */
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the relatedTransaction
	 */
	public String getRelatedTransaction() {
		return relatedTransaction;
	}
	/**
	 * @param relatedTransaction the relatedTransaction to set
	 */
	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}
	
	@Override
	public String toString() {
		return transactionId + ":" + fromAccountId + ":" + toAccountId 
				+ ":" + createdAt + ":" + amount + ":" + transactionType 
				+ ":" + relatedTransaction;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(transactionId, 
        			   transaction.getTransactionId()) &&
               Objects.equals(fromAccountId, 
            		   transaction.getFromAccountId()) &&
               Objects.equals(toAccountId, 
            		   transaction.getToAccountId()) &&
               Objects.equals(createdAt, 
            		   transaction.getCreatedAt()) &&
               Objects.equals(amount, 
            		   transaction.getAmount()) &&
               Objects.equals(transactionType, 
            		   transaction.getTransactionType())&&
               Objects.equals(relatedTransaction, 
            		   transaction.getRelatedTransaction());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(transactionId, fromAccountId, toAccountId, 
				createdAt, amount, transactionType, relatedTransaction);
	}	
}
