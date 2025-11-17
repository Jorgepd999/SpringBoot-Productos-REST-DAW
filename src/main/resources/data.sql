INSERT INTO CATEGORIAS (id, nombre) VALUES 
(1, 'Electrónica'),
(2, 'Hogar y Cocina'),
(3, 'Libros y Medios'),
(4, 'Deportes y Ocio'),
(5, 'Oficina y Papelería');

INSERT INTO PRODUCTOS (nombre, descripcion, precio, peso, altura, anchura, profundidad, fecha_creacion, categoria_id) VALUES 
('Smartphone X10', 'Teléfono de alta gama con cámara 50MP.', 999.99, 0.180, 0.16, 0.08, 0.01, NOW(), 1), -- Electrónica
('Tablet Pro 11"', 'Ideal para diseño gráfico y productividad.', 649.00, 0.450, 0.25, 0.18, 0.01, NOW(), 1), -- Electrónica
('Auriculares Inalámbricos', 'Cancelación de ruido activa, 20h batería.', 149.50, 0.050, 0.05, 0.05, 0.04, NOW(), 1), -- Electrónica
('Teclado Mecánico RGB', 'Diseño compacto, switches táctiles.', 89.99, 0.850, 0.03, 0.35, 0.15, NOW(), 1), -- Electrónica

('Batidora de Vaso', 'Potente motor de 1000W para smoothies.', 75.00, 2.100, 0.40, 0.15, 0.15, NOW(), 2), -- Hogar y Cocina
('Juego de Sartenes Antiadherentes', 'Set de 3 sartenes de aluminio forjado.', 55.90, 1.500, 0.05, 0.30, 0.25, NOW(), 2), -- Hogar y Cocina
('Cafetera Espresso', 'Automática, para café en grano o molido.', 299.99, 7.500, 0.35, 0.20, 0.30, NOW(), 2), -- Hogar y Cocina
('Tostadora Retro 2 Ranuras', 'Acabado en cromo, función descongelar.', 35.50, 1.200, 0.20, 0.25, 0.12, NOW(), 2), -- Hogar y Cocina

('Ficción: El Último Códice', 'Novela de misterio y aventuras.', 19.95, 0.300, 0.22, 0.15, 0.03, NOW(), 3), -- Libros y Medios
('Manual de Spring Boot', 'Guía completa para desarrollo con Java.', 45.00, 0.800, 0.24, 0.17, 0.05, NOW(), 3), -- Libros y Medios
('Colección de Poesía Clásica', 'Edición de tapa dura con ilustraciones.', 29.99, 0.650, 0.20, 0.13, 0.04, NOW(), 3), -- Libros y Medios
('Revista de Tecnología - Edición #50', 'Especial sobre Inteligencia Artificial.', 8.50, 0.150, 0.28, 0.21, 0.01, NOW(), 3), -- Libros y Medios

('Colchoneta de Yoga', 'Extra gruesa, material antideslizante.', 24.99, 1.100, 0.01, 1.80, 0.60, NOW(), 4), -- Deportes y Ocio
('Balón de Fútbol Talla 5', 'Piel sintética de alta resistencia.', 35.00, 0.450, 0.22, 0.22, 0.22, NOW(), 4), -- Deportes y Ocio
('Mancuernas Ajustables (Par)', 'Peso total de 20kg, fácil de almacenar.', 120.00, 20.000, 0.10, 0.40, 0.20, NOW(), 4), -- Deportes y Ocio
('Linterna Frontal LED', 'Recargable USB, 5 modos de luz.', 15.90, 0.120, 0.05, 0.08, 0.04, NOW(), 4), -- Deportes y Ocio

('Paquete de 500 Hojas A4', 'Papel blanco de 80gr.', 6.50, 2.500, 0.05, 0.30, 0.21, NOW(), 5), -- Oficina y Papelería
('Impresora Multifunción Láser', 'Imprime, escanea y copia en blanco y negro.', 189.00, 8.500, 0.30, 0.40, 0.35, NOW(), 5), -- Oficina y Papelería
('Set de Bolígrafos de Gel', '12 colores vibrantes, secado rápido.', 12.00, 0.080, 0.15, 0.05, 0.02, NOW(), 5), -- Oficina y Papelería
('Archivador de Palanca', 'Tamaño A4, capacidad para 500 hojas.', 9.75, 0.400, 0.32, 0.28, 0.08, NOW(), 5); -- Oficina y Papelería