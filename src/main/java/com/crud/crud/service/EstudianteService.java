package com.crud.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.crud.dao.EstudianteDAO;
import com.crud.crud.model.Estudiante;

import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteDAO estudianteDAO;

    // Crear un nuevo estudiante
    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteDAO.save(estudiante);
    }

    // Obtener todos los estudiantes
    public Iterable<Estudiante> obtenerTodosEstudiantes() {
        return estudianteDAO.findAll();
    }

    // Obtener un estudiante por su ID
    public Optional<Estudiante> obtenerEstudiantePorId(Long id) {
        return estudianteDAO.findById(id);
    }

    // Actualizar un estudiante
    public Estudiante actualizarEstudiante(Long id, Estudiante estudiante) {
        if (estudianteDAO.existsById(id)) {
            estudiante.setId(id);
            return estudianteDAO.save(estudiante);
        }
        return null;
    }

    // Eliminar un estudiante
    public void eliminarEstudiante(Long id) {
        estudianteDAO.deleteById(id);
    }
}