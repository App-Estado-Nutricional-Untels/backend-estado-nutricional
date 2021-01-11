package com.untels.estadonutricional.security.specification.usuario;

import com.untels.estadonutricional.security.entity.Usuario;
import org.springframework.data.jpa.domain.Specification;

public abstract class ListarUsuariosSpecification {

    public static Specification<Usuario> nombresContains(
            String nombres
    ) {
        return (usuario, cq, cb)
                -> cb.like(
                        usuario.get("persona").get("nombre"),
                        "%" + nombres + "%");
    }

    public static Specification<Usuario> apePaternoContains(
            String apePaterno
    ) {
        return (usuario, cq, cb)
                -> cb.like(
                        usuario.get("persona").get("apepaterno"),
                        "%" + apePaterno + "%");
    }

    public static Specification<Usuario> apeMaternoContains(
            String apeMaterno
    ) {
        return (usuario, cq, cb)
                -> cb.like(
                        usuario.get("persona").get("apematerno"),
                        "%" + apeMaterno + "%");
    }

    public static Specification<Usuario> dniContains(
            String dni
    ) {
        return (usuario, cq, cb)
                -> cb.like(
                        usuario.get("persona").get("dni"),
                        "%" + dni + "%");
    }

    public static Specification<Usuario> correoElectronicoContains(
            String cod
    ) {
        return (usuario, cq, cb)
                -> cb.like(
                        usuario.get("correoElectronico"),
                        "%" + cod + "%");
    }

    public static Specification<Usuario> edadMin(
            float edadMin
    ) {
        return (usuario, cq, cb)
                -> cb.greaterThanOrEqualTo(
                        usuario.get("persona").get("edad"),
                        edadMin);
    }

    public static Specification<Usuario> edadMax(
            float edadMax
    ) {
        return (usuario, cq, cb)
                -> cb.lessThanOrEqualTo(
                        usuario.get("persona").get("edad"),
                        edadMax);
    }
}
