package com.abubakar.service;

import java.util.List;

import com.abubakar.model.Order;
import com.abubakar.model.Seller;
import com.abubakar.model.Transaction;

public interface TransactionService {
	
	Transaction createTransaction(Order order);
	List<Transaction> getTransactionBySellerId(Seller seller);
	List<Transaction> getAllTransactions();
	
}
