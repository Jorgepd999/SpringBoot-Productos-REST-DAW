package es.etg.daw.dawes.java.rest.restfull;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.CategoriaRepository;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.CategoriaFactory;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.CategoriaRepositoryMockImpl;

public class CategoriaRepositoryMockImplTest {

    private CategoriaRepository repository;

    @BeforeEach
    void setUp() {
        repository = new CategoriaRepositoryMockImpl();
    }

    @Test
    void save() {
        // Arrange
         var categoria= CategoriaFactory.create();

        // Act
        Categoria c = repository.save(categoria);

        // Assert
        assertAll(
           () -> assertNotNull(c), //Categoria no es nula
           () -> assertNotNull(c.getId()), //la categoria creada tiene id
           () -> assertNotNull(repository.getById(c.getId())) // si lo busco lo debo recuperar *opcional
        );
    }

    @Test
    void getAll() {
        List<Categoria> categorias = repository.getAll();

        assertAll(
            () -> assertNotNull(categorias),
            () -> assertEquals(CategoriaFactory.getDemoData().size(), categorias.size())
        );
    }

   

    @Test
    void getById() {
        // Arrange
        CategoriaId id = new CategoriaId(1);

        // Act
        Optional<Categoria> resultado = repository.getById(id);

        // Assert
        assertEquals(id, resultado.get().getId());
    }

    @Test
    void deleteById() {
        // Arrange
        CategoriaId id = new CategoriaId(1);
        
        // Act
        repository.deleteById(id);

        // Assert
        assertAll(
            () -> assertTrue(repository.getById(id).isEmpty())
        );
    }
}
