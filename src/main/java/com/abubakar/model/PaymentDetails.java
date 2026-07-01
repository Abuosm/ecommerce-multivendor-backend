package com.abubakar.model;



// import com.abubakar.dto.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDetails {
 
  private String paymentId;
  private String razorpayPaymentLinkId;
  private String razorpayPaymentLinkRefereId;
  private String razorpayPaymentLinkStatus;
  private String razorpayPaymentIdZWSP;
  private String status;
}
