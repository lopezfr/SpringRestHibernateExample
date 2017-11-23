package com.umg.util;

import com.umg.model.Usuario;
import com.umg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by carloscubur on 20/11/17.
 * Para validar que un usuario tiene monedas aun para poder realizar la trx
 */
public class ValidaTrx {

    @Autowired
    UsuarioService usuarioService;

    //Recibe id de usuario devuelve si valido
    public boolean validaSaldo(int id){
        boolean pasa = false;
        //Recibe el objeto transaccion
        //Valida que el usuario aun tenga monedas
        Usuario usuario = new Usuario();
        int cod = id;
        System.out.println("Validando disponibilidad de usuario: " + cod);
        System.out.println("Llamando service");
        usuario = usuarioService.getUsuario(cod);
        System.out.println("Usuario: " + usuario.getnombreUsuario());
        System.out.println("Monedas: " + usuario.getCoins());
        if (usuario.getCoins() > 0) {
/*
            //Convierte el JSON Objeto a una cadena de texto para enviar a la cola
            //Convierte Objeto a String en formato JSON
            Gson gson = new Gson();
            String jsonInString = gson.toJson(transaccion);
            System.out.println("Objeto JSON convertido a texto:   " + jsonInString);

            boolean envio = enviaMsjCola.enviaMensajeaMQ(jsonInString);

            //Si esta OK agregamos la transaccion a la BD
            if (envio) {
                transaccionService.addTransaccion(transaccion);
                usuario.setCoins(usuario.getCoins() - transaccion.getQuantity());
                usuarioService.updateUsuario(usuario);
            }*/
            pasa=true;
        }
        return pasa;
    }

    //Actualiza el saldo de monedas del usuario
    public boolean ActualizaSaldo(int id){
        boolean pasa=false;
        return pasa;
    }
}
