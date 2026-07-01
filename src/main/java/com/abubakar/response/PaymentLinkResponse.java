package com.abubakar.response;

import lombok.Data;

@Data
public class PaymentLinkResponse {

	private String payOnDelivery;
	private String payment_link_url;
	private String payemnt_link_id;
}
