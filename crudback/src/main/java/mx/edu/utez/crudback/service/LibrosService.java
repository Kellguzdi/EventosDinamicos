package mx.edu.utez.crudback.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import mx.edu.utez.crudback.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.crudback.model.Libros;
import mx.edu.utez.crudback.model.LibrosRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibrosService {
    @Autowired
    LibrosRepository repository;

    public List<Libros> buscarPorNombre(String nombre) {
        return repository.findByTitleContainingIgnoreCase(nombre);
    }

    public List<Libros> buscarPorAutor(String autor) {
        return repository.findAllByAutorNameContainingIgnoreCase(autor);
    }

    public List<Libros> buscarPorFechas(Date inicio, Date fin) {
        return repository.findByIssueDateBetween(inicio, fin);
    }

    public List<Libros> buscarPorCategoria(String categoria) {
        return repository.findAllByCategoriaNameCategory(categoria);
    }

    public List<Libros> buscarTodasOrdenadasPorFechaDesc() {
        return repository.findAllByOrderByIssueDateDesc();
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Libros>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //servicio para obtener un libro por id
    public Optional<Libros> findBookById(Long id) {
        return repository.findById(id);
    }

    public CustomResponse<Libros> update(Libros libro){
        return new CustomResponse<>(
                this.repository.save(libro),
                false,
                200,
                "ok"
        );
    }



    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libros> insert(Libros libro){
        return new CustomResponse<>(
                this.repository.save(libro),
                false,
                200,
                "ok"
        );
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Libros> delete(Long id){
        this.repository.deleteById(id);
        return new CustomResponse<>(
                null,
                false,
                200,
                "ok"
        );
    }






}
