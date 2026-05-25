package com.example.moattravel2.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	//宿泊人数が定員以下かどうかチェックする
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
		
		return numberOfPeople <=capacity;
	}
	
	//宿泊料金の計算
	public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price) {
		
		long numberOfNights = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
		
		int amount = price*(int) numberOfNights;
		
		return amount;
		
		
	}
}
