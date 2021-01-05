package com.untels.estadonutricional.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {

    private String correoElectronico;
    private String clave;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(
            String correoElectronico,
            String clave,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(
                new SimpleGrantedAuthority(
                        usuario
                                .getRol()
                                .getRolNombre()
                                .name()));

        return new UsuarioPrincipal(
                usuario.getCorreoElectronico(),
                usuario.getClave(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
