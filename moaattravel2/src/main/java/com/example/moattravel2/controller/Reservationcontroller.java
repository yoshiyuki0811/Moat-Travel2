package com.example.moattravel2.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.moattravel2.entity.Reservation;
import com.example.moattravel2.entity.User;
import com.example.moattravel2.repository.ReservationRepository;
import com.example.moattravel2.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Reservationcontroller {
	
	private final ReservationRepository reservationRepository;
	
	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailImpl, @PageableDefault(page=0, size = 10, sort ="id",direction =Direction.ASC)Pageable pageable, Model model) {
		
		User user = userDetailImpl.getUser();
		
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		
		model.addAttribute("reservationPage", reservationPage);
		
		return "reservations/index";
	}
	

}
