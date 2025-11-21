package es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria.EditCategoriaCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.error.CategoriaNotFoundException;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditCategoriaUseCase {
    private final CategoriaRepository categoriaRepository;

    public Categoria update(EditCategoriaCommand command){

        return categoriaRepository.getById(command.id())
                .map(p -> {
                    p.setNombre(command.nombre());
                    return categoriaRepository.save(p);})
                    .orElseThrow( ()-> new CategoriaNotFoundException(command.id().getValue()));
                
    }
    
}

