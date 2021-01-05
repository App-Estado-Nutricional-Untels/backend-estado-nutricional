package com.untels.estadonutricional.service;

import com.untels.estadonutricional.repository.CodigoUniValidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoUniValidoService {

    @Autowired
    private CodigoUniValidoRepository codigoUniValidoRepository;
}
