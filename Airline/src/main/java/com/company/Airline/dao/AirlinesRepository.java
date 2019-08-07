package com.company.Airline.dao;

import com.company.Airline.dto.Airlines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlinesRepository extends JpaRepository<Airlines, Integer> {
}

