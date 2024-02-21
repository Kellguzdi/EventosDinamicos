package mx.edu.utez.crudback.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "books")
@Data
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable = false)
    private String title;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "issueDate", nullable = false)
    private Date issueDate;
    @ManyToOne()
    @JoinColumn(name = "author_id")
    private Autor autor;
    @ManyToOne( )
    @JoinColumn(name = "genero_id")

    private Categoria categoria;
}
