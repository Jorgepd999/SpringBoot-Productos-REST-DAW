package es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria;

import java.util.List;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.error.CategoriaNotFoundException;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindCategoriaUseCase {
    private final CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        List<Categoria> categorias = categoriaRepository.getAll();
        if (categorias.isEmpty())
            throw new CategoriaNotFoundException();
        return categorias;
    }
}
