package com.example.moattravel2.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.example.moattravel2.entity.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SignupEventPublisher {
	
	private final ApplicationEventPublisher applicationPublisher;
	
	public void publishSignupEvent(User user, String requestUrl){
		
		applicationPublisher.publishEvent(new SignupEvent(this, user,requestUrl));
		
		
	}
	
	

}
