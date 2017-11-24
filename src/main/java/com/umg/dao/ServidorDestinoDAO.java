package com.umg.dao;

import java.util.List;
import com.umg.model.ServidorDestino;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by carloscubur on 16/11/17.
 */

@Repository
public class ServidorDestinoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    //recupera todos los servidores
    public List<ServidorDestino> getAllServidorDestino(){
        Session session = this.sessionFactory.getCurrentSession();
        List<ServidorDestino> servidorDestinoList = session.createQuery("from servidordestino").list();
        return servidorDestinoList;
    }

    //recupera solo 1 servidor con el id
    public ServidorDestino getServidorDestino(int id){
        Session session = this.sessionFactory.getCurrentSession();
        ServidorDestino servidorDestino = (ServidorDestino) session.load(ServidorDestino.class, new Integer(id));
        return servidorDestino;
    }
}
