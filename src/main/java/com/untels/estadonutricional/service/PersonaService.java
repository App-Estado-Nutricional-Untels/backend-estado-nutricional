package com.untels.estadonutricional.service;

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
}
