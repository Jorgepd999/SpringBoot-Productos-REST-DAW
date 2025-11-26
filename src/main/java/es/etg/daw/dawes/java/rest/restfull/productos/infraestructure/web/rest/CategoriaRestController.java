package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria.CreateCategoriaCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria.EditCategoriaCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.service.categoria.CreateCategoriaService;
import es.etg.daw.dawes.java.rest.restfull.productos.application.service.categoria.FindCategoriaService;
import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria.DeleteCategoriaUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria.EditCategoriaUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.mapper.CategoriaMapper;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.CategoriaRequest;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.CategoriaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categorias") 
@RequiredArgsConstructor
public class CategoriaRestController {

    private final CreateCategoriaService createCategoriaService;
    private final FindCategoriaService findCategoriaService;
    private final DeleteCategoriaUseCase deleteCategoriaService;
    private final EditCategoriaUseCase editCategoriaService;

           // Recuperamos la versión desde el properties
    @Value("${api.version}")
    private String apiVersion;
    // MÉTODO DE VALIDACIÓN, comprueba la version de la API, si es distinta da error.
    private void checkApiVersion() {
        if (!"1.0".equals(apiVersion)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Versión del API incorrecta: " + apiVersion
            );
        }
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> createCategoria(
            @Valid @RequestBody CategoriaRequest categoriaRequest) {
                checkApiVersion();
        CreateCategoriaCommand comando = CategoriaMapper.toCommand(categoriaRequest);
        Categoria categoria = createCategoriaService.createCategoria(comando);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaMapper.toResponse(categoria));

    }

    @GetMapping
    public List<CategoriaResponse> allCategorias() {
        checkApiVersion();
        return findCategoriaService.findAll()
                .stream()
                .map(CategoriaMapper::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable int id) {
        checkApiVersion();
        deleteCategoriaService.delete(new CategoriaId(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // Metodo put
    public CategoriaResponse editProducto(@PathVariable int id, @Valid @RequestBody CategoriaRequest categoriaRequest) {
        checkApiVersion();
        EditCategoriaCommand comando = CategoriaMapper.toCommand(id, categoriaRequest);
        Categoria categoria = editCategoriaService.update(comando);
        return CategoriaMapper.toResponse(categoria); // Respuesta
    }

}
