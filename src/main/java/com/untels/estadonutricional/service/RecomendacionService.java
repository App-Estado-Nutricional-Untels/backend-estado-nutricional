package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.RecomendacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;
}
