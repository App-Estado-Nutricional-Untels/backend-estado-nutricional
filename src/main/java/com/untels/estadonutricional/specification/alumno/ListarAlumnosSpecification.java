package com.untels.estadonutricional.specification.alumno;

import com.untels.estadonutricional.entity.Alumno;
import org.springframework.data.jpa.domain.Specification;

public abstract class ListarAlumnosSpecification {

    public static Specification<Alumno> nombresContains(
            String nombres
    ) {
        return (alumno, cq, cb)
                -> cb.like(
                        alumno.get("persona").get("nombre"),
                        "%" + nombres + "%");
    }

    public static Specification<Alumno> apePaternoContains(
            String apePaterno
    ) {
        return (alumno, cq, cb)
                -> cb.like(
                        alumno.get("persona").get("apepaterno"),
                        "%" + apePaterno + "%");
    }

    public static Specification<Alumno> apeMaternoContains(
            String apeMaterno
    ) {
        return (alumno, cq, cb)
                -> cb.like(
                        alumno.get("persona").get("apematerno"),
                        "%" + apeMaterno + "%");
    }

    public static Specification<Alumno> dniContains(
            String dni
    ) {
        return (alumno, cq, cb)
                -> cb.like(
                        alumno.get("persona").get("dni"),
                        "%" + dni + "%");
    }

    public static Specification<Alumno> codigoUniversitarioContains(
            String cod
    ) {
        return (alumno, cq, cb)
                -> cb.like(
                        alumno.get("codigoUniversitario"),
                        "%" + cod + "%");
    }

    public static Specification<Alumno> edadMin(
            float edadMin
    ) {
        return (alumno, cq, cb)
                -> cb.greaterThanOrEqualTo(
                        alumno.get("persona").get("edad"),
                        edadMin);
    }

    public static Specification<Alumno> edadMax(
            float edadMax
    ) {
        return (alumno, cq, cb)
                -> cb.lessThanOrEqualTo(
                        alumno.get("persona").get("edad"),
                        edadMax);
    }
}
