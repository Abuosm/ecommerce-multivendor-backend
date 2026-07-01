package com.abubakar.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abubakar.model.Order;
import com.abubakar.model.Seller;
import com.abubakar.model.Transaction;
import com.abubakar.repository.SellerRepository;
import com.abubakar.repository.TransactionRepository;
import com.abubakar.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final SellerRepository sellerRepository;
	
	@Override
	public Transaction createTransaction(Order order) {
		
		Seller seller = sellerRepository.findById(order.getSellerId()).get();
		
		Transaction transaction = new Transaction();
		transaction.setSeller(seller);
		transaction.setCustomer(order.getUser());
		transaction.setOrder(order);
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTransactionBySellerId(Seller seller) {
		
		return transactionRepository.findBySellerId(seller.getId());
	}

	@Override
	public List<Transaction> getAllTransactions() {
		
		return transactionRepository.findAll();
	}

}
