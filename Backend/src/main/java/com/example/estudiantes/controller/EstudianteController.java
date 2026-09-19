package com.example.estudiantes.controller;

import com.example.estudiantes.model.Estudiantes;
import com.example.estudiantes.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@CrossOrigin(origins = "*") // Permite peticiones desde frontend (como Angular, React, etc.)
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // 1. Listar todos los estudiantes
    @GetMapping
    public List<Estudiantes> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    // 2. Buscar estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiantes> obtenerEstudiantePorId(@PathVariable Long id) {
        return estudianteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Guardar un nuevo estudiante
    @PostMapping
    public Estudiantes guardarEstudiante(@RequestBody Estudiantes estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // 4. Actualizar un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<Estudiantes> actualizarEstudiante(@PathVariable Long id,
            @RequestBody Estudiantes detallesEstudiante) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.setCodigo(detallesEstudiante.getCodigo());
            estudiante.setDni(detallesEstudiante.getDni());
            estudiante.setNombres(detallesEstudiante.getNombres());
            estudiante.setApellidos(detallesEstudiante.getApellidos());
            estudiante.setCorreo(detallesEstudiante.getCorreo());
            estudiante.setCarrera(detallesEstudiante.getCarrera());

            Estudiantes actualizado = estudianteRepository.save(estudiante);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarEstudiante(@PathVariable Long id) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudianteRepository.delete(estudiante);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}