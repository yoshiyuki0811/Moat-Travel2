package com.example.moattravel2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.moattravel2.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StripeWebhookController {
	
	private final StripeService stripeSevice;
	
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	
	@Value("${stripe.webhook-secret}")
	private String webhookSecret;
	
@PostMapping("/stripe/wabhook")
public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-signature")String sigHeader) {
	
	Stripe.apiKey =stripeApiKey;
	
	Event event= null;
	
	try {
		event = Webhook.constructEvent(payload, sigHeader, sigHeader);
		
	}catch(SignatureVerificationException e) {
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		
		}
	if("checkout.session.completed".equals(event.getType())) {
		
		stripeSevice.processSessionCompleted(event);
		
	}
	return new ResponseEntity<> ("Succes",HttpStatus.OK);
}
}

