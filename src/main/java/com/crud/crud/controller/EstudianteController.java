package com.crud.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.crud.crud.model.Estudiante;
import com.crud.crud.service.EstudianteService;

import jakarta.validation.Valid;

import java.util.Optional;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Listar todos los estudiantes
    @GetMapping("/listar")
    public String obtenerTodosEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.obtenerTodosEstudiantes());
        return "lista";  // Vista lista.html
    }

    // Vista para agregar un estudiante
    @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "agregar";  // Vista agregar.html
    }

    // Crear un estudiante
    @PostMapping("/nuevo")
    public String crearEstudiante(@Valid @ModelAttribute Estudiante estudiante , BindingResult result, Model model) {

        if (result.hasErrors()) {
            // Si hay errores de validación, vuelve al formulario
            return "agregar";
        }
        estudianteService.crearEstudiante(estudiante);
        return "redirect:/estudiantes/listar";  // Redirigir a la lista
    }

    // Vista para editar un estudiante
    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEdicion(@PathVariable Long id, Model model) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorId(id);
        if (estudiante.isPresent()) {
            model.addAttribute("estudiante", estudiante.get());
            return "editar";  // Vista editar.html
        } else {
            return "redirect:/estudiantes";  // Si no se encuentra, redirigir a la lista
        }
    }

    // Actualizar un estudiante
    @PostMapping("/editar/{id}")
    public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute Estudiante estudiante) {
        estudiante.setId(id);
        estudianteService.actualizarEstudiante(id, estudiante);
        return "redirect:/estudiantes/listar";  // Redirigir a la lista
    }

    // Eliminar un estudiante
    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return "redirect:/estudiantes/listar";  // Redirigir a la lista
    }
}