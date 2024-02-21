package mx.edu.utez.crudback.controller;

import mx.edu.utez.crudback.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import mx.edu.utez.crudback.service.CategoriaService;
import mx.edu.utez.crudback.utils.CustomResponse;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @GetMapping("/")
    public CustomResponse<List<Categoria>> getAll() {
        return service.getAll();
    }
}
