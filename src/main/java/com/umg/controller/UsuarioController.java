package com.umg.controller;

import java.util.List;

import com.umg.model.Usuario;
import com.umg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by carloscubur on 16/11/17.
 */

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    //Con requestmapping hacemos los direccionamiento de las url

    //Obtener un Json con todos los usuarios
    @RequestMapping(value = "/getAllUsuarios", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarioList = usuarioService.getAllUsuarios();
        return usuarioList;
    }

    //Obtener un Json segun el id que mandemos en la url
    @RequestMapping(value = "/getUsuario/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Usuario getUsuarioById(@PathVariable("id") int id) {
        Usuario usuario=usuarioService.getUsuario(id);
        System.out.println("Usuario: \n\n"+usuario.getnombreUsuario());
        return usuario;
    }

    //crear un nuevo usuario enviandole un JSON
    @RequestMapping(value = "/addUsuario", method = RequestMethod.POST, headers = "Accept=application/json")
    public void addUsuario(@RequestBody Usuario usuario) {
        usuarioService.addUsuario(usuario);
    }

    //actualiza un usuario enviandole un JSON
    @RequestMapping(value = "/updateUsuario", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updateUsuario(@RequestBody Usuario usuario) {
        usuarioService.updateUsuario(usuario);
    }

    //Elimina un usuario segun el id que mandemos en la url
    @RequestMapping(value = "/deleteUsuario/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteUsuario(@PathVariable("id") int id) {
        usuarioService.deleteUsuario(id);
    }
}
