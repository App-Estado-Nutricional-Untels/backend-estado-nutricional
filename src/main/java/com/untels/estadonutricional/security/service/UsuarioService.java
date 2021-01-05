package com.untels.estadonutricional.security.service;

import com.untels.estadonutricional.security.entity.Usuario;
import com.untels.estadonutricional.security.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getOneByCorreoElectronico(
            String correoElectronico
    ) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    public boolean existsByCorreoElectronico(String correoElectronico) {
        return usuarioRepository.existsByCorreoElectronico(correoElectronico);
    }

    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
