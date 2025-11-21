package es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCategoriaUseCase {
    private final CategoriaRepository categoriaRepository;
    public void delete (CategoriaId id){
        categoriaRepository.deleteById(id);
    }
    
}

