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

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock.CategoriaFactory;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.CategoriaRequest;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.CategoriaResponse;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoResponse;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoriaControllerIT {
    public static String ENDPOINT = "/categorias";

     // Json
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

      // Para metodos que tienen una request
    @Autowired
    private JacksonTester<CategoriaRequest> jsonCategoriaRequest;

    @BeforeEach
    public void setUp(){
        mapper= new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

   @Test
@Order(1)
public void When_Get_AllCategorias_Expect_Lista() throws Exception {
    int numCategorias = CategoriaFactory.getDemoData().values().size();

    // Realizo la petición
    MockHttpServletResponse response = mockMvc.perform(
        get(ENDPOINT).accept(MediaType.APPLICATION_JSON)
    ).andReturn().getResponse();

    // Leo la respuesta JSON y la convierto en lista
    List<CategoriaResponse> res = mapper.readValue(
        response.getContentAsString(),
        mapper.getTypeFactory().constructCollectionType(List.class, CategoriaResponse.class)
    );

    // Verifico resultados
    assertAll(
        () -> assertEquals(HttpStatus.OK.value(), response.getStatus()),
        () -> assertTrue(res.size() == numCategorias)
    );
}
@Test
@Order(10)
public void When_Post_CreateCategoria() throws  Exception{
    Categoria nuevo = CategoriaFactory.create();
    CategoriaRequest req = new CategoriaRequest(nuevo);

        //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonCategoriaRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

            //Gestiono la respuesta
        CategoriaResponse res = mapper.readValue(response.getContentAsString(), CategoriaResponse.class);

                    //Evaluo la salida
        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.CREATED.value()), //Ha ido bien
                () -> assertEquals(res.nombre(), nuevo.getNombre())
               
        );
    }

     @Test
    @Order(11)
    public void Error_ValidationError_When_CreateCategoria_EmptyNombre() throws Exception{
        Categoria nuevo = CategoriaFactory.create();
        nuevo.setNombre(null);

        CategoriaRequest req = new CategoriaRequest(nuevo);
        

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                        post(ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonCategoriaRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


            // Comprobamos
        assertAll(
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus())
        );
    }

     @Test
    @Order(20)
    public void When_Put_EditCategoria() throws Exception{
        Categoria nuevo = CategoriaFactory.create();
        nuevo.setId(new CategoriaId(1));

       CategoriaRequest req = new CategoriaRequest(nuevo);

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                            // productos/{id} -> productos/1
                        put(ENDPOINT+"/"+nuevo.getId().getValue())
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonCategoriaRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

            //Gestiono la respuesta
        ProductoResponse res = mapper.readValue(response.getContentAsString(), ProductoResponse.class);

                    //Evaluo la salida
        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.OK.value()), //Ha ido bien
                () -> assertEquals(res.nombre(), nuevo.getNombre()),
                () -> assertEquals(res.id(), nuevo.getId().getValue())
        );
    }

     @Test
    @Order(30)
    public void When_Delete_DeleteCategoria() throws Exception{
        Categoria nuevo = CategoriaFactory.create();
        nuevo.setId(new CategoriaId(1));

       CategoriaRequest req = new CategoriaRequest(nuevo);

            //Realizo la petición POST
        MockHttpServletResponse response = mockMvc.perform(
                            // productos/{id} -> productos/1
                        delete(ENDPOINT+"/"+nuevo.getId().getValue())
                                .contentType(MediaType.APPLICATION_JSON)
                                // Le paso el body
                                .content(jsonCategoriaRequest.write(req).getJson())
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        assertAll(
                () -> assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value()) //Ha ido bien
        );
    }
}



