package com.umg.service;

import java.util.List;

import com.umg.dao.TransaccionDAO;
import com.umg.model.Transaccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by carloscubur on 16/11/17.
 */

@Service("transaccionService")
public class TransaccionService {

    @Autowired
    TransaccionDAO transaccionDAO;

    @Transactional
    public List<Transaccion> getAllTransacciones(){return transaccionDAO.getAllTransacciones();}

    @Transactional
    public Transaccion getTransaccion(int id){
        return transaccionDAO.getTransaccion(id);
    }

    @Transactional
    public void addTransaccion(Transaccion transaccion){transaccionDAO.addTransaccion(transaccion);}

    @Transactional
    public void updateTransaccion(Transaccion transaccion){transaccionDAO.updateTransaccion(transaccion);}

    @Transactional
    public void deleteTransaccion(int id){transaccionDAO.deleteTransaccion(id);}
}
