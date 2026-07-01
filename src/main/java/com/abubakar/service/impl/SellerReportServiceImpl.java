package com.abubakar.service.impl;

import org.springframework.stereotype.Service;

import com.abubakar.model.Seller;
import com.abubakar.model.SellerReport;
import com.abubakar.repository.SellerReportRepository;
import com.abubakar.service.SellerReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
	
	private final SellerReportRepository sellerReportRepository;

	@Override
	public SellerReport getSellerReport(Seller seller) {
		
		SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId()); 
		if(sellerReport == null) {
			SellerReport newReport = new SellerReport();
			newReport.setSeller(seller);
			return sellerReportRepository.save(newReport);
		}
		return sellerReport;
	}

	@Override
	public SellerReport updateSellerReport(SellerReport sellerReport) {
		
		return sellerReportRepository.save(sellerReport);
	}

}
