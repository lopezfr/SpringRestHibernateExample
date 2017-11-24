package com.umg.service;

import java.util.List;

import com.umg.dao.TrxOperadaDAO;
import com.umg.model.TrxOperada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by carloscubur on 24/11/17.
 */

@Service("trxOperadaService")
public class TrxOperadaService {

    @Autowired
    TrxOperadaDAO trxOperadaDAO;

    @Transactional
    public List<TrxOperada> getAllTrxOperadas(){
        return trxOperadaDAO.getAllTrxOperadas();
    }

    @Transactional
    public TrxOperada getTrxOperada(int id){
        return trxOperadaDAO.getTrxOperada(id);
    }

    @Transactional
    public void addTrxOperada(TrxOperada trxOperada){
        trxOperadaDAO.addTrxOperada(trxOperada);
    }
}
