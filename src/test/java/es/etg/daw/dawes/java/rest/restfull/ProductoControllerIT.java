package es.etg.daw.dawes.java.rest.restfull;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.ProductoFactory;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoRequest;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoResponse;

// Indicamos que es un test de Spring
@SpringBootTest
// Configuramos el cliente MVC
@AutoConfigureMockMvc
// Configuramos los testers de JSON (recuerda que usabamos json en postman)
@AutoConfigureJsonTesters
// Limpiamos el contexto antes de cada test (queremos que todas las pruebas se
// ejecuten sin datos de otras)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProductoControllerIT {
    // Constante para las rutas
    public static String ENDPOINT = "/productos";

    // Json
    private ObjectMapper mapper = new ObjectMapper();

    // Cargamos el cliente MVC
    @Autowired
    MockMvc mockMvc;

    // Para metodos que tienen una request
    @Autowired
    private JacksonTester<ProductoRequest> jsonProductoRequest;

  

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        // Necesitamos registrar este módulo al usar LocalDate en nuestros beans
        // para que funcione bien el mapper de json
        mapper.registerModule(new JavaTimeModule());

    }

     @Test
    @Order(1) //Quiero que se ejecute la primera
    public void When_Get_AllProductos_Expect_Lista() throws Exception {
            //Productos esperados
        int numProductos = ProductoFactory.getDemoData().values().size();

            // Realizo la petición
        MockHttpServletResponse response = mockMvc.perform(
                //método get de http
            get(ENDPOINT).accept(MediaType.APPLICATION_JSON) 
        ).andReturn().getResponse();


            //Gestiono la respuesta
        List<ProductoResponse>  res = mapper.readValue(response.getContentAsString(),
                mapper.getTypeFactory().constructCollectionType(List.class, ProductoResponse.class));

        
            //Evaluo la salida
        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.OK.value()), //Ha ido bien
                () -> assertTrue(res.size() == numProductos)
        );
    }

    @Test
    @Order(10)
    public void When_Post_CreateProducto() throws Exception{
        Producto nuevo = ProductoFactory.create();

        ProductoRequest req = new ProductoRequest(nuevo);

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonProductoRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

            //Gestiono la respuesta
        ProductoResponse res = mapper.readValue(response.getContentAsString(), ProductoResponse.class);

                    //Evaluo la salida
        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.CREATED.value()), //Ha ido bien
                () -> assertEquals(res.nombre(), nuevo.getNombre()),
                () -> assertEquals(res.precio(), nuevo.getPrecio()),
                () -> assertEquals(res.categoria(), nuevo.getCategoria().getValue()),
                () -> assertTrue(res.id()>0)
        );
    }
    /**
     * Si se crea un producto sin nombre debe dar MethodArgumentNotValidException
     *  la respuesta debería ser HttpStatus.BAD_REQUEST
     */
    @Test
    @Order(11)
    public void Error_ValidationError_When_CreateProducto_EmptyNombre() throws Exception{
        Producto nuevo = ProductoFactory.create();
        nuevo.setNombre(null);

        ProductoRequest req = new ProductoRequest(nuevo);
        

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonProductoRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


            // Comprobamos
        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus())
        );
    }


    @Test
    @Order(20)
    public void When_Put_EditProducto() throws Exception{
        Producto nuevo = ProductoFactory.create();
        nuevo.setId(new ProductoId(1));

        ProductoRequest req = new ProductoRequest(nuevo);

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                            // productos/{id} -> productos/1
                        put(ENDPOINT+"/"+nuevo.getId().getValue())
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonProductoRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

            //Gestiono la respuesta
        ProductoResponse res = mapper.readValue(response.getContentAsString(), ProductoResponse.class);

                    //Evaluo la salida
        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.OK.value()), //Ha ido bien
                () -> assertEquals(res.nombre(), nuevo.getNombre()),
                () -> assertEquals(res.precio(), nuevo.getPrecio()),
                () -> assertEquals(res.categoria(), nuevo.getCategoria().getValue()),
                () -> assertEquals(res.id(), nuevo.getId().getValue())
        );
    }


    @Test
    @Order(30)
    public void When_Delete_DeleteProducto() throws Exception{
        Producto nuevo = ProductoFactory.create();
        nuevo.setId(new ProductoId(1));

        ProductoRequest req = new ProductoRequest(nuevo);

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                            // productos/{id} -> productos/1
                        delete(ENDPOINT+"/"+nuevo.getId().getValue())
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonProductoRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value()) //Ha ido bien
        );
    }

}
