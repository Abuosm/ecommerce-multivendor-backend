package com.abubakar.service;

import com.abubakar.model.Seller;
import com.abubakar.model.SellerReport;

public interface SellerReportService {
	
	SellerReport getSellerReport(Seller seller);
	SellerReport updateSellerReport(SellerReport sellerReport);

}
