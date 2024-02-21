INSERT INTO authors (id, name) VALUES
                                   (1, 'Juan Pérez'),
                                   (2, 'María López'),
                                   (3, 'Carlos García'),
                                   (4, 'Ana Fernández'),
                                   (5, 'Luis González'),
                                   (6, 'Sofía Rodríguez'),
                                   (7, 'José Martínez'),
                                   (8, 'Laura Hernández'),
                                   (9, 'Patricia Gómez'),
                                   (10, 'Manuel Jiménez');

-- Insertando registros en la tabla `categories`
INSERT INTO categories (id, name_category) VALUES
                                               (1, 'Ficción'),
                                               (2, 'No Ficción'),
                                               (3, 'Educativo'),
                                               (4, 'Ciencia Ficción'),
                                               (5, 'Biografía'),
                                               (6, 'Autoayuda'),
                                               (7, 'Historia'),
                                               (8, 'Infantil'),
                                               (9, 'Cocina'),
                                               (10, 'Arte');

-- Insertando registros en la tabla `books`
INSERT INTO books (id, image, issue_date, title, author_id, genero_id) VALUES

                                                                           (1, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2021-01-01', 'El viaje del tiempo', 1, 1),
                                                                           (2, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2022-01-01', 'La realidad detrás del espejo', 2, 2),
                                                                           (3, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2020-01-01', 'Aprendiendo SQL', 3, 3),
                                                                           (4, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2019-01-01', 'Las estrellas son nuestro hogar', 4, 4),
                                                                           (5, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2018-01-01', 'Vida de un viajero', 5, 5),
                                                                           (6, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2021-01-01', 'Encuentra tu camino', 6, 6),
                                                                           (7, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2017-01-01', 'Relatos del pasado', 7, 7),
                                                                           (8, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2022-01-01', 'Aventuras de Luna', 8, 8),
                                                                           (9, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2020-01-01', 'Sabores del mundo', 9, 9),
                                                                           (10, 'https://i.pinimg.com/originals/75/a2/25/75a225e90e32569cc86172b2f7356613.jpg', '2019-01-01', 'El arte de la creación', 10, 10);