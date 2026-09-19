package com.example.estudiantes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.estudiantes.model.Estudiantes;
import com.example.estudiantes.repository.EstudianteRepository;

@Service
public class EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public Estudiantes guardar(Estudiantes estudiante) {
        return repository.save(estudiante);
    }

    public List<Estudiantes> listar() {
        return repository.findAll();
    }
}
