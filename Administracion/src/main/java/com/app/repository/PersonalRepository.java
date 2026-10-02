package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Personal;

public interface PersonalRepository extends JpaRepository<Personal, Long> {
}