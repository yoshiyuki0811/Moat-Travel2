package com.example.moattravel2.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moattravel2.entity.House;
import com.example.moattravel2.entity.Reservation;
import com.example.moattravel2.entity.User;
import com.example.moattravel2.form.ReservatonInputForm;
import com.example.moattravel2.repository.HouseRepository;
import com.example.moattravel2.repository.ReservationRepository;
import com.example.moattravel2.security.UserDetailsImpl;
import com.example.moattravel2.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationRepository reservationRepository;
	
	private final ReservationService reservationService;
	
	private final HouseRepository houseRepository;
	
	
	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailImpl, @PageableDefault(page=0, size = 10, sort ="id",direction =Direction.ASC)Pageable pageable, Model model) {
		
		User user = userDetailImpl.getUser();
		
		Page<Reservation> reservationPage = reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
		
		model.addAttribute("reservationPage", reservationPage);
		
		return "reservations/index";
	}
	
	@GetMapping("/houses/{id}/reservations/input")
	public String input(
			@PathVariable(name ="id")Integer id,
			@ModelAttribute @Validated ReservatonInputForm reservationInputForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		
		House house = houseRepository.getReferenceById(id);
		
		Integer numberOfPeople = reservationInputForm.getNumberOfPeople();
		
		Integer capacity =house.getCapacity();
		
		return "redirect:/houses/{id}/reservations/confirm";
	}
	
	

}
