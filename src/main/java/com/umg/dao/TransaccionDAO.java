package com.umg.dao;

import com.umg.model.Transaccion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by carloscubur on 16/11/17.
 */

@Repository
public class TransaccionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    //Recupera todas las transacciones hechas
    public List<Transaccion> getAllTransacciones(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Transaccion> transaccionList = session.createQuery("from transaccion").list();
        return transaccionList;
    }

    //Recupera solo 1 transaccion con el id
    public Transaccion getTransaccion(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Transaccion transaccion = (Transaccion) session.get(Transaccion.class, new Integer(id));
        return transaccion;
    }

    //Crea una transaccion
    public Transaccion addTransaccion(Transaccion transaccion){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(transaccion);
        return transaccion;
    }

    //Actualiza una transaccion
    public void updateTransaccion(Transaccion transaccion){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(transaccion);
    }

    //Elimina una transaccion
    public void deleteTransaccion(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Transaccion transaccion = (Transaccion) session.get(Transaccion.class, new Integer(id));
        if (null != transaccion){
            session.delete(transaccion);
        }
    }
}
