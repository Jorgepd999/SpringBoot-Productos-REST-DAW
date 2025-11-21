package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.rest;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<CategoriaResponse> createCategoria(
            @Valid @RequestBody CategoriaRequest categoriaRequest) {
        CreateCategoriaCommand comando = CategoriaMapper.toCommand(categoriaRequest);
        Categoria categoria = createCategoriaService.createCategoria(comando);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaMapper.toResponse(categoria));

    }

    @GetMapping
    public List<CategoriaResponse> allCategorias() {
        return findCategoriaService.findAll()
                .stream()
                .map(CategoriaMapper::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable int id) {
        deleteCategoriaService.delete(new CategoriaId(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // Metodo put
    public CategoriaResponse editProducto(@PathVariable int id, @Valid @RequestBody CategoriaRequest categoriaRequest) {
        EditCategoriaCommand comando = CategoriaMapper.toCommand(id, categoriaRequest);
        Categoria categoria = editCategoriaService.update(comando);
        return CategoriaMapper.toResponse(categoria); // Respuesta
    }

}
