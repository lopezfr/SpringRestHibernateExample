package com.umg.dao;

import com.umg.model.TrxOperada;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by carloscubur on 23/11/17.
 */

@Repository
public class TrxOperadaDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    //Recupera todas las transacciones hechas
    public List<TrxOperada> getAllTrxOperadas(){
        Session session = this.sessionFactory.getCurrentSession();
        List<TrxOperada> trxOperadaList = session.createQuery("from TrxOperada").list();
        return trxOperadaList;
    }

    //Recupera una trx en especifico.
    public TrxOperada getTrxOperada(int id){
        Session session = this.sessionFactory.getCurrentSession();
        TrxOperada trxOperada = (TrxOperada) session.get(TrxOperada.class, new Integer(id));
        return trxOperada;
    }

    //Crea una trx operada en base a otra que ya existe.
    public TrxOperada addTrxOperada(TrxOperada trxOperada){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(trxOperada);
        return trxOperada;
    }
}
