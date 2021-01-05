package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.DatoAntropometricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatoAntropometricoService {

    @Autowired
    private DatoAntropometricoRepository antropometricoRepository;
}
