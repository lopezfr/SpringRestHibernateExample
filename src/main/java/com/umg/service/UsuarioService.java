package com.umg.service;

import java.util.List;

import com.umg.dao.UsuarioDAO;
import com.umg.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by carloscubur on 16/11/17.
 */
@Service("usuarioService")
public class UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Transactional
    public List<Usuario> getAllUsuarios() {
        return usuarioDAO.getAllUsuarios();
    }

    @Transactional
    public Usuario getUsuario(int id) {
        Usuario usuario = usuarioDAO.getUsuario(id);
        return usuario;
    }

    @Transactional
    public void addUsuario(Usuario usuario) {
        usuarioDAO.addUsuario(usuario);
    }

    @Transactional
    public void updateUsuario(Usuario usuario) {
        usuarioDAO.updateUsuario(usuario);
    }

    @Transactional
    public void deleteUsuario(int id) {
        usuarioDAO.deleteUsuario(id);
    }
}
