package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto.CreateProductoCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto.EditProductoCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.service.producto.CreateProductoService;
import es.etg.daw.dawes.java.rest.restfull.productos.application.service.producto.FindProductoService;
import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.producto.DeleteProductoUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.producto.EditProductoUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.mapper.ProductoMapper;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoRequest;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/productos") // La url será /productos
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
public class ProductoController {

    private final CreateProductoService createProductoService;
    private final FindProductoService findProductoService;
    private final DeleteProductoUseCase deleteProductoService;
    private final EditProductoUseCase editProductoService;

    // Recuperamos la versión desde el properties
    @Value("${api.version}")
    private String apiVersion;

    // MÉTODO DE VALIDACIÓN, comprueba la version de la API, si es distinta da
    // error.
    private void checkApiVersion() {
        if (!"1.0".equals(apiVersion)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Versión del API incorrecta: " + apiVersion);
        }
    }

    @Operation(summary = "Obtiene el listado de productos", description = "Busca en la base de datos todos los productos y sus detalles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos generado"),
            @ApiResponse(responseCode = "404", description = "No hay productos en la base de datos")
    })

    @PostMapping // Método Post
    public ResponseEntity<ProductoResponse> createProducto(
            // Indicamos que valide los datos de la request
            @Valid @RequestBody ProductoRequest productoRequest) {
        checkApiVersion();
        CreateProductoCommand comando = ProductoMapper.toCommand(productoRequest);
        Producto producto = createProductoService.createProducto(comando);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductoMapper.toResponse(producto)); // Respuesta
    }

    @GetMapping
    public List<ProductoResponse> allProductos() {
        checkApiVersion();
        // if(true) throw new NullPointerException();
        return findProductoService.findAll()
                .stream() // Convierte la lista en un flujo
                .map(ProductoMapper::toResponse) // Mapeamos/Convertimos cada elemento del flujo (Producto) en un objeto
                                                 // de Respuesta (ProductoResponse)
                .toList(); // Lo devuelve como una lista.

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable int id) {
        checkApiVersion();
        deleteProductoService.delete(new ProductoId(id)); // convertimos id en ProductoId
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // Metodo put
    public ProductoResponse editProducto(@PathVariable int id, @RequestBody ProductoRequest productoRequest) {
        checkApiVersion();
        EditProductoCommand comando = ProductoMapper.toCommand(id, productoRequest);
        Producto producto = editProductoService.update(comando);
        return ProductoMapper.toResponse(producto); // Respuesta
    }

    // Método que captura los errores y devuelve un mapa con el campo que no cumple
    // la validación y un mensaje de error.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        checkApiVersion();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
