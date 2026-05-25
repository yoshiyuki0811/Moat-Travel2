package com.example.moattravel2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moattravel2.entity.Reservation;
import com.example.moattravel2.entity.User;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Integer> {
	
	public Page<Reservation> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
	

}
