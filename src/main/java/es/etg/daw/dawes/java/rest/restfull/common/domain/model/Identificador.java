package es.etg.daw.dawes.java.rest.restfull.common.domain.model;

import lombok.Data;

@Data
public abstract class Identificador {
    private final Integer value;

    protected Identificador(Integer value) {
        if (value == null ) {
            throw new IllegalArgumentException("El identificador no puede ser nulo");
        }
        this.value = value;
    }
}
