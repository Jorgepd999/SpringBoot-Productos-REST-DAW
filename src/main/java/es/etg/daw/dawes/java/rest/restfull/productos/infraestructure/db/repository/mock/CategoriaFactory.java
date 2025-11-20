package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.repository.mock;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Categoria;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;

public class CategoriaFactory {

    public static final Map<CategoriaId, Categoria> getDemoData(){

        Map<CategoriaId, Categoria> datos = new LinkedHashMap<>();

        datos.put(new CategoriaId(1), new Categoria(new CategoriaId(1), "Categoria 1", LocalDateTime.now()));
        datos.put(new CategoriaId(2), new Categoria(new CategoriaId(2), "Categoria 2", LocalDateTime.now()));
        return datos;
    }
    public static Categoria create(){
       
        return new Categoria(new CategoriaId(3),"Nueva categoria",LocalDateTime.now());
    }
}
