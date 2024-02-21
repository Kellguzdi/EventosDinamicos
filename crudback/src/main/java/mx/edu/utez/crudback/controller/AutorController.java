package mx.edu.utez.crudback.controller;

import mx.edu.utez.crudback.model.Autor;
import mx.edu.utez.crudback.service.AutorService;
import mx.edu.utez.crudback.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:5173")
public class AutorController {
    @Autowired
    private AutorService service;

    @GetMapping("/")
    public CustomResponse<List<Autor>> getAll() {
        return service.getAll();
    }
}
