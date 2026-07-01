package com.abubakar.controller;

import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abubakar.model.Order;
import com.abubakar.model.PaymentOrder;
import com.abubakar.model.Seller;
import com.abubakar.model.SellerReport;
import com.abubakar.model.User;
import com.abubakar.response.ApiResponse;
import com.abubakar.response.PaymentLinkResponse;
import com.abubakar.service.OrderService;
import com.abubakar.service.PaymentService;
import com.abubakar.service.SellerReportService;
import com.abubakar.service.SellerService;
import com.abubakar.service.TransactionService;
import com.abubakar.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
	
	private final PaymentService paymentService;
	private final UserService userService;
	private final SellerService sellerService;
	private final OrderService orderService;
	private final SellerReportService sellerReportService;
	private final TransactionService transactionService;
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<ApiResponse> paymentSuccessHandler(
			@PathVariable String paymentId,
			@RequestParam String paymentLinkedId,
			@RequestHeader("Authorization") String jwt
			) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		PaymentLinkResponse paymentResponse;
		
		PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentLinkedId);
		
		boolean paymentSuccess = paymentService.ProceedPaymentOrder(paymentOrder, paymentId, paymentLinkedId);
		
		
		if(paymentSuccess) {
			for(Order order:paymentOrder.getOrders() ) {
				transactionService.createTransaction(order);
				Seller seller = sellerService.getSellerById(order.getSellerId());
				SellerReport report = sellerReportService.getSellerReport(seller);
				report.setTotalOrders(report.getTotalOrders()+1);
				report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
				report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
				sellerReportService.updateSellerReport(report);
			}
		}
		
		ApiResponse res = new ApiResponse();
		res.setMessage("payment successfull");
		
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.CREATED);
	}
	

}
