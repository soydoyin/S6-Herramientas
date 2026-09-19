package com.example.estudiantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estudiantes.model.Estudiantes;

/**
 * EstudianteRepository
 */
public interface EstudianteRepository extends JpaRepository<Estudiantes, Long> {

}