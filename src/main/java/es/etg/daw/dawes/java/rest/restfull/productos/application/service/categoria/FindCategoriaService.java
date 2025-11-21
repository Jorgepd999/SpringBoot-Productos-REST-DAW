package es.etg.daw.dawes.java.rest.restfull.productos.application.service.categoria;

import java.util.List;

import org.springframework.stereotype.Service;

import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria.FindCategoriaUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCategoriaService {
     private final FindCategoriaUseCase findCategoriaUseCase;


     public List<Categoria> findAll() {
        return findCategoriaUseCase.findAll();
     }
}
