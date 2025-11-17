package es.etg.daw.dawes.java.rest.restfull;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.ProductoRepository;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.ProductoFactory;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.ProductoRepositoryMockImpl;

public class ProductoRepositoryMockImplTest {

    ProductoRepository repository;

    @BeforeEach
    void setUp() {
        // Inicializamos las el repositorio
        repository = new ProductoRepositoryMockImpl();
    }

    @Test
    void save() {
        // Arrange
        var producto = ProductoFactory.create();
        // Act
        Producto p = repository.save(producto);
        // Asset
        assertAll(
                () -> assertNotNull(p), // el producto no es nulo
                () -> assertNotNull(p.getId()), // el producto creado tiene id
                () -> assertNotNull(repository.getById(p.getId())) // si lo busco lo debo recuperar *opcional
        );

    }

    @Test
    void getAll() {
        // Act
        var productos = repository.getAll();
        // Assert
        assertAll(
                () -> assertNotNull(productos),
                () -> assertEquals(ProductoFactory.getDemoData().size(), productos.size()));
    }

    @Test

    void getById() {
        // Arrange
        ProductoId id = new ProductoId(1);
        // Act
        Optional<Producto> p = repository.getById(id);
        // Assert
        assertTrue(p.isPresent());
        assertEquals(id, p.get().getId());
    }

    
    @Test

    void deleteById() {
        // Arrange
        ProductoId id = new ProductoId(1);
        // Act
        repository.deleteById(id);
        // Assert
        assertAll(
                () -> assertTrue(repository.getById(id).isEmpty()));
    }

}
