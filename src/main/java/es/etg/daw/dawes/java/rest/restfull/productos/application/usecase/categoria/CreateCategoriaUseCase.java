package es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria;

import java.time.LocalDateTime;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria.CreateCategoriaCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CreateCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public Categoria create(CreateCategoriaCommand comando){
        Categoria categoria = Categoria.builder()           
                              .nombre(comando.nombre())
                              .createdAt(LocalDateTime.now()).build();
        
        return categoriaRepository.save(categoria);
    }
}

