package com.crud.crud.dao;



import org.springframework.data.repository.CrudRepository;
import com.crud.crud.model.Estudiante;

public interface EstudianteDAO extends CrudRepository<Estudiante, Long> {
    // Puedes agregar consultas personalizadas si lo necesitas
}