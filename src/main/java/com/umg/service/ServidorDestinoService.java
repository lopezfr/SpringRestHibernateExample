package com.umg.service;

import java.util.List;

import com.umg.dao.ServidorDestinoDAO;
import com.umg.model.ServidorDestino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by carloscubur on 16/11/17.
 */

@Service("servidorDestinoService")
public class ServidorDestinoService {


    @Autowired
    ServidorDestinoDAO servidorDestinoDAO;

    @Transactional
    public List<ServidorDestino> getAllServidorDestino(){return servidorDestinoDAO.getAllServidorDestino();}

    @Transactional
    public ServidorDestino getServidorDestino(int id){return servidorDestinoDAO.getServidorDestino(id);}
}
