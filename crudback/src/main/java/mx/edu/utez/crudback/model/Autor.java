package mx.edu.utez.crudback.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "authors")
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;


}
