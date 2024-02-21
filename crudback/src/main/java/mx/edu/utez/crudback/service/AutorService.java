package mx.edu.utez.crudback.service;

import mx.edu.utez.crudback.model.Autor;
import mx.edu.utez.crudback.model.AutorRepository;
import mx.edu.utez.crudback.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AutorService {
     @Autowired
    AutorRepository repository;


     @Transactional(readOnly = true)
     public CustomResponse<List<Autor>> getAll(){
         return new CustomResponse<>(
                 this.repository.findAll(),
                 false,
                 200,
                 "Ok"
         );
     }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Autor> insert(Autor Autor){
        return new CustomResponse<>(
                this.repository.save(Autor),
                false,
                200,
                "ok"
        );
    }

}
