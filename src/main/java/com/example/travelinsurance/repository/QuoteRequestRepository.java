package com.example.travelinsurance.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelinsurance.model.QuoteRequest;

public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
}