package com.rentalmotor.repository;

import com.rentalmotor.model.Motor; // Perbaiki import ini
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorRepository extends JpaRepository<Motor, Long> {
}