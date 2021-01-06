package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public boolean existePorCodigoMedico(String codigo) {
        return medicoRepository.existsByCodigoMedico(codigo);
    }
}
