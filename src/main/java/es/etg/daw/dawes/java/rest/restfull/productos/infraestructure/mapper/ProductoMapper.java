package es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto.CreateProductoCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.application.command.producto.EditProductoCommand;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.CategoriaId;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.Producto;
import es.etg.daw.dawes.java.rest.restfull.productos.domain.model.ProductoId;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.jpa.entity.CategoriaJpaEntity;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.db.jpa.entity.ProductoJpaEntity;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoRequest;
import es.etg.daw.dawes.java.rest.restfull.productos.infraestructure.web.dto.ProductoResponse;

public class ProductoMapper {

    public static CreateProductoCommand toCommand(ProductoRequest productoRequest) {
        return new CreateProductoCommand(productoRequest.nombre(), productoRequest.precio(),
                new CategoriaId(productoRequest.categoriaId()));
    }

    public static EditProductoCommand toCommand(int id, ProductoRequest productoRequest) {
        // pasamos del int a ProductoId
        return new EditProductoCommand(new ProductoId(id), productoRequest.nombre(), productoRequest.precio(),
                new CategoriaId(productoRequest.categoriaId()));
    }

    public static ProductoResponse toResponse(Producto producto) {
        return new ProductoResponse(producto.getId().getValue(), // lo pasamos a int
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCreatedAt(),
                producto.getCategoria().getValue());// Agregamos la categoria.
    }

    public static ProductoJpaEntity toEntity(Producto p) {

        // Defino la categoría
        CategoriaJpaEntity cat = new CategoriaJpaEntity();
        cat.setId(p.getCategoria().getValue());
        ProductoId id = p.getId();
        return ProductoJpaEntity.builder().id(id!=null?id.getValue():null)
                                        .nombre(p.getNombre())
                                       .precio(new BigDecimal(p.getPrecio()))
                                       .fechaCreacion(p.getCreatedAt())
                                       .categoria(cat)
                                       .build();

    }
    public static Producto toDomain(ProductoJpaEntity p) {
        return Producto.builder().id(new ProductoId(p.getId()))
                .nombre(p.getNombre())
                .precio(p.getPrecio().doubleValue())
                .createdAt(p.getFechaCreacion())
                .categoria(new CategoriaId(p.getCategoria().getId()))
                .build();

    }

    public static List<Producto> toDomain(List<ProductoJpaEntity> lista) {
        List<Producto> lp = new ArrayList<>();
        for (ProductoJpaEntity pe : lista) {
            lp.add(toDomain(pe));
        }
        return lp;
    }
}
