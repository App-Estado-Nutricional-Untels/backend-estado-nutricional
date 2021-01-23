package com.untels.estadonutricional.service;

import com.untels.estadonutricional.entity.Persona;
import com.untels.estadonutricional.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public boolean existePorDni(String dni) {
        return personaRepository.existsByDni(dni);
    }
    
    public Persona obtenerPorId(int id){
        return personaRepository.findById(id);
    }

    public boolean existePersonaPorId(int id){
        return personaRepository.existsById(id);
    }
    
    public void guardar(Persona persona) {
        personaRepository.save(persona);
    }
}
