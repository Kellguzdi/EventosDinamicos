package mx.edu.utez.crudback.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LibrosRepository extends JpaRepository<Libros,Long>{
    List<Libros> findByTitleContainingIgnoreCase(String title);

    List<Libros> findAllByAutorNameContainingIgnoreCase(String nombreAutor);

    List<Libros> findByIssueDateBetween(Date fechaInicio, Date fechaFin);

    List<Libros> findAllByCategoriaNameCategory(String nameCategory);


    List<Libros> findAllByOrderByIssueDateDesc();
}
