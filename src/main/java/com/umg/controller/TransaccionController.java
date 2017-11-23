package com.umg.controller;

import java.util.List;

import com.google.gson.Gson;
import com.umg.model.Transaccion;
import com.umg.model.Usuario;
import com.umg.queue.EnviaMsjCola;
import com.umg.service.TransaccionService;
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
public class TransaccionController {

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    UsuarioService usuarioService;


    //con requesmapping hacemos los direccionamientos de las URL

    //Obtener un Json con todas las transacciones
    @RequestMapping(value = "/getAllTransacciones", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Transaccion> getTransacciones() {
        System.out.println("Rest devuelve todas las transacciones");
        List<Transaccion> transaccionList = transaccionService.getAllTransacciones();
        return transaccionList;
    }

    //Obtener un Json segun el id que mandemos en la url
    @RequestMapping(value = "/getTransaccion/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Transaccion getTransaccionById(@PathVariable int id) {
        return transaccionService.getTransaccion(id);
    }

    //Crear una nueva transaccion enviandole un JSON
    @RequestMapping(value = "/addTransaccion", method = RequestMethod.POST, headers = "Accept=application/json")
    public void addTransaccion(@RequestBody Transaccion transaccion) {
        System.out.println("Antes de grabar trx valida usuario y coins");
        Usuario usuario = usuarioService.getUsuario(transaccion.getIdUsuario());
        System.out.println("Usuario: " + usuario.getnombreUsuario());
        System.out.println("Monedas: " + usuario.getCoins());
        //Si las monedas son suficientes intenta enviar al servidor de colas
        if (usuario.getCoins() >= transaccion.getQuantity()) {

            //Convierte el JSON Objeto a una cadena de texto para enviar a la cola
            //Convierte Objeto a String en formato JSON
            Gson gson = new Gson();
            String jsonInString = gson.toJson(transaccion);
            System.out.println("Objeto JSON convertido a texto:   " + jsonInString);
            EnviaMsjCola enviaMsjCola = new EnviaMsjCola();
            boolean envio = enviaMsjCola.enviaMensajeaMQ(jsonInString);
            //Si esta OK agregamos la transaccion a la BD
            if (envio) {
                transaccionService.addTransaccion(transaccion);
                //Actualiza la cantidad de monedas del usuario
                System.out.println("Nueva cantidad de monedas: " + (usuario.getCoins() - transaccion.getQuantity()));
                usuario.setCoins(usuario.getCoins() - transaccion.getQuantity());
                usuarioService.updateUsuario(usuario);
            }
        } else {
            System.out.println("monedas insuficientes para la transaccion");
        }

    }

    //Actualiza una transaccion enviandole un JSON
    @RequestMapping(value = "/updateTransaccion", method = RequestMethod.PUT, headers = "Accept=application/json")
    public void updateTransaccion(@RequestBody Transaccion transaccion) {
        transaccionService.updateTransaccion(transaccion);
    }

    //Elimina una transaccion segun el id que mandemos en la url
    @RequestMapping(value = "/deleteTransaccion/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public void deleteTransaccion(@PathVariable("id") int id) {
        transaccionService.deleteTransaccion(id);
    }
}
