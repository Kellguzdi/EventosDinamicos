package mx.edu.utez.crudback.controller;

import java.util.Date;
import java.util.List;

import jakarta.validation.Valid;
import mx.edu.utez.crudback.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.edu.utez.crudback.model.Libros;
import mx.edu.utez.crudback.service.LibrosService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class LibroController {
    @Autowired
    private LibrosService service;

    @GetMapping("/buscarPorNombre")
    public List<Libros> buscarPorNombre(@RequestParam String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @GetMapping("/buscarPorAutor")
    public List<Libros> buscarPorAutor(@RequestParam String autor) {
        return service.buscarPorAutor(autor);
    }

    @GetMapping("/buscarPorFechas")
    public List<Libros> buscarPorFechas(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        return service.buscarPorFechas(inicio, fin);
    }

    @GetMapping("/buscarPorCategoria")
    public List<Libros> buscarPorCategoria(@RequestParam String categoria) {
        return service.buscarPorCategoria(categoria);
    }

    @GetMapping("/ordenadasPorFechaDesc")
    public List<Libros> buscarTodasOrdenadasPorFechaDesc() {
        return service.buscarTodasOrdenadasPorFechaDesc();
    }

    @GetMapping("/")
    public CustomResponse<List<Libros>> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libros> getBookById(@PathVariable Long id) {
        return service.findBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public CustomResponse<Libros> addNewProcedureType(@RequestBody Libros libros) {
        return service.insert(libros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    // servicio para actualizar libro por id
    @PutMapping("/{id}")
    public ResponseEntity<Libros> update(@PathVariable Long id, @Valid @RequestBody Libros libros) {
        if (!service.findBookById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        libros.setId(id);
        return ResponseEntity.ok(service.update(libros).getData());
    }
}
