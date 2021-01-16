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

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUnoPorCorreoElectronico(
            String correoElectronico
    ) {
        return usuarioRepository.findByCorreoElectronico(correoElectronico);
    }

    public boolean existePorCorreoElectronico(String correoElectronico) {
        return usuarioRepository.existsByCorreoElectronico(correoElectronico);
    }

    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    
    public void eliminarUsuarioPorId(int id){
        usuarioRepository.deleteById(id);
    }
    
    public boolean existePorId(int id){
        return usuarioRepository.existsById(id);
    }
}
