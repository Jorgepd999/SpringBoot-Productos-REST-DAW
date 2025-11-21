package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.CategoriaRepository;

@Repository
public class CategoriaRepositoryMockImpl implements CategoriaRepository {

    private final Map<CategoriaId, Categoria> categorias = CategoriaFactory.getDemoData();

    //Mejora para calcular el id de la creación que viene vacío
  @Override
    public Categoria save(Categoria t) {
            //create
        if(t.getId()==null) t.setId(new CategoriaId(obtenerSiguienteId()));

        categorias.put(t.getId(), t);
        return t;
    }

    private int obtenerSiguienteId(){
        CategoriaId ultimo =new CategoriaId(0);

        if(!categorias.isEmpty()){
            Collection<Categoria> lista = categorias.values();
            
            for (Categoria c : lista) {
                ultimo = c.getId();
            } 

        }
        return ultimo.getValue()+1;
    }

    @Override
    public List<Categoria> getAll() {
        return new ArrayList<>(categorias.values());
    }

    @Override
    public Optional<Categoria> getById(CategoriaId id) {
        // Un optional puede tener una valor o no. Si no existe el producto devuelve
        // Optional.empty
        return Optional.ofNullable(categorias.get(id));
    }

    @Override
    public void deleteById(CategoriaId id) {
        categorias.remove(id);
    }

    /*
     * @Override No está incluido en CategoriaRepository.
     * public Optional<Categoria> getByName(String name) {
     * // TODO Sin implementar
     * throw new UnsupportedOperationException("Unimplemented method 'getByName'");
     * }
     */
}
