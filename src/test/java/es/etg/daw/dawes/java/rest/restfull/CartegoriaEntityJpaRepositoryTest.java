package es.etg.daw.dawes.java.rest.restfull;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.jpa.repository.CategoriaEntityJpaRepository;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.CategoriaFactory;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.mapper.CategoriaMapper;




@DataJpaTest(showSql = true)
public class CartegoriaEntityJpaRepositoryTest {
    
    @Autowired
    private CategoriaEntityJpaRepository repository;

    @Test
    @Order(1)
    void findAll() {

        var categorias = repository.findAll();


        assertAll(
                () -> assertNotNull(categorias),
                () -> assertTrue(!categorias.isEmpty())
        );
    }

    @Test
    @Order(2)
    void findById(){
        update(); // Actualizo y recupero el dato controlado
        var p = CategoriaFactory.create(); //Categoria actualizado antes, vamos a ve rsi está bien

        var cat = repository.findById(p.getId().getValue()).get();

        assertAll(
            () -> assertNotNull(cat),
            () -> assertEquals(cat.getId(), p.getId().getValue()),
            () -> assertEquals(cat.getNombre(), p.getNombre())
        );
    }


    

    @Test
    @Order(5)
    void create(){
        var nuevo = CategoriaMapper.toEntity(CategoriaFactory.create());
        nuevo.setId(null);
        var cat = repository.save(nuevo);

        assertAll(
            () -> assertNotNull(cat),
            () -> assertTrue(cat.getId()!=null)
            );
    }

    @Test
    @Order(10)
    void update(){
        var nuevo = CategoriaMapper.toEntity(CategoriaFactory.create());
        var producto = repository.save(nuevo);

                assertAll(
                () -> assertNotNull(producto),
                () -> assertTrue(producto.getId()!=null)
        );
    }


    @Test
    @Order(15)
    void delete(){
        var cat = repository.findById(1); 
        repository.delete(cat.get());

        var catEliminada = repository.findById(1).isEmpty();
        assertAll(
            () -> assertTrue(catEliminada)
        );
    }

}