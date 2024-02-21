package mx.edu.utez.crudback.service;

import mx.edu.utez.crudback.model.Categoria;
import mx.edu.utez.crudback.model.CategoriaRepository;
import mx.edu.utez.crudback.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Categoria>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }
}
