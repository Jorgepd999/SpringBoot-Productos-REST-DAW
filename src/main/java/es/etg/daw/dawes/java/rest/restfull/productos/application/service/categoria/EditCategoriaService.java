package es.etg.daw.dawes.java.rest.restfull.productos.application.service.categoria;

import org.springframework.stereotype.Service;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.categoria.EditCategoriaCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.usecase.categoria.EditCategoriaUseCase;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EditCategoriaService {
       private final EditCategoriaUseCase editCategoriaUseCase;

    public Categoria update(EditCategoriaCommand editCategoriaCommand) {
      
        return  editCategoriaUseCase.update(editCategoriaCommand);
    }
}

