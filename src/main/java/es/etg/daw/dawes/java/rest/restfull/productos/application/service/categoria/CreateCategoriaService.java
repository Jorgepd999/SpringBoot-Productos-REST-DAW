package es.etg.daw.dawes.java.rest.restfull.productos.application.service.categoria;

import org.springframework.stereotype.Service;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria.CreateCategoriaCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria.CreateCategoriaUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class CreateCategoriaService {
    private final CreateCategoriaUseCase createCategoriaUseCase;

    public Categoria createCategoria(CreateCategoriaCommand comando){
        Categoria categoria = createCategoriaUseCase.create(comando);
        return categoria;
    }
}

