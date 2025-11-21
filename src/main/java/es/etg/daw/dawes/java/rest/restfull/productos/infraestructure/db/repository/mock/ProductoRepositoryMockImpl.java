package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.repository.ProductoRepository;

@Repository
public class ProductoRepositoryMockImpl implements ProductoRepository {

    private final Map<ProductoId, Producto> productos = ProductoFactory.getDemoData();

    // Mejora para calcular el id de la creación que viene vacío
        @Override
    public Producto save(Producto t) {
            //create
        if(t.getId()==null) t.setId(new ProductoId(obtenerSiguienteId()));

        productos.put(t.getId(), t);
        return t;
    }

    private int obtenerSiguienteId(){
        ProductoId ultimo = new ProductoId(0);
        if(!productos.isEmpty()){
            Collection<Producto> lista = productos.values();
            
            for (Producto p : lista) {
                ultimo = p.getId();
            }

        }
        return ultimo.getValue()+1;
    }

    @Override
    public List<Producto> getAll() {
        return new ArrayList<>(productos.values());
    }

    @Override
    public Optional<Producto> getById(ProductoId id) {
        // Un optional puede tener una valor o no. Si no existe el producto devuelve
        // Optional.empty
        return Optional.ofNullable(productos.get(id));
    }

    @Override
    public void deleteById(ProductoId id) {
        productos.remove(id);
    }

    @Override
    public Optional<Producto> getByName(String name) {
        // TODO Sin implementar
        throw new UnsupportedOperationException("Unimplemented method 'getByName'");
    }

}
