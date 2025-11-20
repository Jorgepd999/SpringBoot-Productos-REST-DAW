package es.etg.daw.dawes.java.rest.restfull;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.jpa.repository.ProductoEntityJpaRepository;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.ProductoFactory;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.mapper.ProductoMapper;




@DataJpaTest(showSql = true)
public class ProductoEntityJpaRepositoryTest {
    
    @Autowired

    private ProductoEntityJpaRepository repository;




    @Test
    @Order(1)
    void findAll() {

        var productos = repository.findAll();


        assertAll(
                () -> assertNotNull(productos),
                () -> assertTrue(!productos.isEmpty())
        );
    }

    @Test
    @Order(2)
    void findById(){
        final double PRECISION = 0.001;
        update(); // Actualizo y recupero el dato controlado
        var p = ProductoFactory.create(); //Producto cambiado antes, vamos a ve si está bien

        var producto = repository.findById(p.getId().getValue()).get();

        assertAll(
            () -> assertNotNull(producto),
            () -> assertEquals(producto.getId(), p.getId().getValue()),
            () -> assertEquals(producto.getNombre(), p.getNombre()),
            () -> assertEquals(producto.getPrecio().doubleValue(), p.getPrecio(), PRECISION)
        );
    }


    @Test
    @Order(3)
    void findByName(){
        final double PRECISION = 0.001;
        update(); // Actualizo y recupero el dato controlado
        var p = ProductoFactory.create(); //Producto cambiado antes, vamos a ve rsi está bien

        var producto = repository.findByNombre(p.getNombre());

        assertAll(
            () -> assertNotNull(producto),
            () -> assertEquals(producto.getId(), p.getId().getValue()),
            () -> assertEquals(producto.getNombre(), p.getNombre()),
            () -> assertEquals(producto.getPrecio().doubleValue(), p.getPrecio(), PRECISION)
        );
    }    

    @Test
    @Order(5)
    void create(){
        var nuevo = ProductoMapper.toEntity(ProductoFactory.create());
        nuevo.setId(null);
        var producto = repository.save(nuevo);

                assertAll(
                () -> assertNotNull(producto),
                () -> assertTrue(producto.getId()!=null)
        );
    }

    @Test
    @Order(10)
    void update(){
        var nuevo = ProductoMapper.toEntity(ProductoFactory.create());
        var producto = repository.save(nuevo);

                assertAll(
                () -> assertNotNull(producto),
                () -> assertTrue(producto.getId()!=null)
        );
    }


    @Test
    @Order(15)
    void delete(){
        var producto = repository.findById(1); 
        repository.delete(producto.get());

        var productoEliminado = repository.findById(1).isEmpty();
        assertAll(
            () -> assertTrue(productoEliminado)
        );
    }

}
