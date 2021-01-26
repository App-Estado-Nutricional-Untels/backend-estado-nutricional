package com.untels.estadonutricional.specification.persona;

import com.untels.estadonutricional.entity.Persona;
import org.springframework.data.jpa.domain.Specification;

public abstract class ListarPersonasSpecification {

    public static Specification<Persona> nombresContains(
            String nombres
    ) {
        return (persona, cq, cb)
                -> cb.like(
                        persona.get("nombre"),
                        "%" + nombres + "%");
    }

    public static Specification<Persona> apePaternoContains(
            String apePaterno
    ) {
        return (persona, cq, cb)
                -> cb.like(
                        persona.get("apepaterno"),
                        "%" + apePaterno + "%");
    }

    public static Specification<Persona> apeMaternoContains(
            String apeMaterno
    ) {
        return (persona, cq, cb)
                -> cb.like(
                        persona.get("apematerno"),
                        "%" + apeMaterno + "%");
    }

    public static Specification<Persona> dniContains(
            String dni
    ) {
        return (persona, cq, cb)
                -> cb.like(
                        persona.get("dni"),
                        "%" + dni + "%");
    }

    public static Specification<Persona> correoElectronicoContains(
            String cod
    ) {
        return (persona, cq, cb)
                -> cb.like(
                        persona.get("usuario").get("correoElectronico"),
                        "%" + cod + "%");
    }

    public static Specification<Persona> edadMin(
            float edadMin
    ) {
        return (persona, cq, cb)
                -> cb.greaterThanOrEqualTo(
                        persona.get("edad"),
                        edadMin);
    }

    public static Specification<Persona> edadMax(
            float edadMax
    ) {
        return (persona, cq, cb)
                -> cb.lessThanOrEqualTo(
                        persona.get("edad"),
                        edadMax);
    }
}
