package com.umg.dao;

import com.umg.model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by carloscubur on 16/11/17.
 */
@Repository
public class UsuarioDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    //Recupera todos los usuarios
    public List<Usuario> getAllUsuarios(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Usuario> usuarioList = session.createQuery("from usuario").list();
        return usuarioList;
    }

    //Recupera solo 1 usuario con el id
    public Usuario getUsuario(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Usuario usuario = (Usuario) session.get(Usuario.class, new Integer(id));
        return usuario;
    }

    //Crea un usuario
    public Usuario addUsuario(Usuario usuario){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(usuario);
        return usuario;
    }

    //Actualiza usuario
    public void updateUsuario(Usuario usuario){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(usuario);
    }

    //Elimina usuario
    public void deleteUsuario(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Usuario usuario = (Usuario) session.load(Usuario.class, new Integer(id));
        if (null != usuario){
            session.delete(usuario);
        }
    }
}
