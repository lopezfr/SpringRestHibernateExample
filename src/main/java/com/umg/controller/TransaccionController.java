package com.umg.controller;

import java.util.List;

import com.google.gson.Gson;
import com.umg.model.*;
import com.umg.queue.EnviaMsjCola;
import com.umg.service.ServidorDestinoService;
import com.umg.service.TransaccionService;
import com.umg.service.TrxOperadaService;
import com.umg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created by carloscubur on 16/11/17.
 */

@RestController
public class TransaccionController {

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ServidorDestinoService servidorDestinoService;

    @Autowired
    TrxOperadaService trxOperadaService;


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
    public Resultado addTransaccion(@RequestBody Transaccion transaccion) {

        //Prepara objeto a devolver con resultado
        Resultado resultado = new Resultado();
        System.out.println("Antes de grabar trx valida usuario y coins");
        try {
            Usuario usuario = usuarioService.getUsuario(transaccion.getIdUsuario());

            System.out.println("Usuario: " + usuario.getnombreUsuario());
            System.out.println("Monedas: " + usuario.getCoins());
            //Si las monedas son suficientes intenta enviar al servidor de colas
            if (usuario.getCoins() >= transaccion.getQuantity()) {

                Transaccion trxMensaje = transaccionService.addTransaccion(transaccion);

                //Convierte el JSON Objeto a una cadena de texto para enviar a la cola
                //Convierte Objeto a String en formato JSON
                Gson gson = new Gson();
                String jsonInString = gson.toJson(trxMensaje);
                System.out.println("Objeto JSON convertido a texto:   " + jsonInString);
                EnviaMsjCola enviaMsjCola = new EnviaMsjCola();
                boolean envio = enviaMsjCola.enviaMensajeaMQ(jsonInString);
                //Si esta OK agregamos la transaccion a la BD
                if (envio) {
                    //Actualiza la cantidad de monedas del usuario
                    System.out.println("Nueva cantidad de monedas: " + (usuario.getCoins() - transaccion.getQuantity()));
                    usuario.setCoins(usuario.getCoins() - transaccion.getQuantity());
                    usuarioService.updateUsuario(usuario);
                    resultado.setEstatusTrx("OK");
                    resultado.setMensajeTrx("Transaccion completada");
                }
            } else {
                resultado.setEstatusTrx("ERROR");
                resultado.setMensajeTrx("Transaccion NO completada monedas insuficientes para la transaccion");
                System.out.println("monedas insuficientes para la transaccion");
            }
        } catch (Exception error) {
            resultado.setEstatusTrx("ERROR");
            resultado.setMensajeTrx("Transaccion NO completada Usuario no existe " + error);
            System.out.println("usuario no existe " + error);
        }
        return resultado;
    }

    //Crear una nueva transaccion enviandole un JSON
    @RequestMapping(value = "/processTransaccion", method = RequestMethod.POST, headers = "Accept=application/json")
    public Resultado processTransaccion(@RequestBody Transaccion transaccion) {

        //Prepara objeto a devolver con resultado
        Resultado resultado = new Resultado();

        //Prepara objeto para obtener datos de servidor
        ServidorDestino servidorDestino;

        //Prepara el objeto para procesar una trx
        TrxOperada trxOperada = new TrxOperada();

        System.out.println("Recibiendo trx. valida");
        try {
            //Valida que servidor que trae el json exista en tabla de servidores
            servidorDestino = servidorDestinoService.getServidorDestino(transaccion.getIdPlatformDestiny());

            //Graba la transaccion procesada
            //trxOperada.setIdTrxOperada(); este como es autoincrement no se envia
            trxOperada.setIdTransaccion(transaccion.getIdTransaccion());
            trxOperada.setServerName(servidorDestino.getServername());
            trxOperada.setServerIp(servidorDestino.getServerip());
            trxOperadaService.addTrxOperada(trxOperada);

            resultado.setEstatusTrx("OK");
            resultado.setMensajeTrx("Transaccion completada");

        } catch (Exception error) {
            resultado.setEstatusTrx("ERROR");
            resultado.setMensajeTrx("Transaccion NO pudo ser procesada, servidor destino no existe" + error);
            System.out.println("usuario no existe " + error);
        }
        return resultado;
    }

    //Distribuye la trx al tomcat que debe de ser segun el archivo de servidores
    @RequestMapping(value = "/distTrx", method = RequestMethod.POST, headers = "Accept=application/json")
    public Resultado distTrx(@RequestBody Transaccion transaccion) {
        //Prepara objeto a devolver con resultado
        Resultado resultado = new Resultado();

        //Prepara objeto para obtener datos de servidor
        ServidorDestino servidorDestino;

        try {
            //Valida que servidor que trae el json exista en tabla de servidores
            servidorDestino = servidorDestinoService.getServidorDestino(transaccion.getIdPlatformDestiny());

            // enviar objeto transaccion.... a docker en el puerto 8080
            Transaccion response = new Transaccion();

            try {
                Client client;
                client = Client.create();
                client.setConnectTimeout(10000);
                client.setReadTimeout(60000);

                //Envia un POST para distribuir
                WebResource service = client
                        .resource("http://"+servidorDestino.getServerip().trim()+":8080/SpringRestHibernateExample/processTransaccion");

                Transaccion request = new Transaccion();

                request.setIdTransaccion(transaccion.getIdTransaccion());
                request.setIdUsuario(transaccion.getIdUsuario());
                request.setIdCoin(transaccion.getIdCoin());
                request.setIdPlatformOrigin(transaccion.getIdPlatformOrigin());
                request.setIdPlatformDestiny(transaccion.getIdPlatformDestiny());
                request.setIdProduct(transaccion.getIdProduct());
                request.setMount(transaccion.getMount());
                request.setQuantity(transaccion.getQuantity());

                System.out.println("Objeto:");
                System.out.println("idTransaccion     " + request.getIdTransaccion());
                System.out.println("idUsuario         " + request.getIdUsuario());
                System.out.println("idCoin            " + request.getIdCoin());
                System.out.println("idPlatformOrigin  " + request.getIdPlatformOrigin());
                System.out.println("idPlatformDestiny " + request.getIdPlatformDestiny());
                System.out.println("idProduct         " + request.getIdProduct());
                System.out.println("Mount             " + request.getMount());
                System.out.println("Quantity          " + request.getQuantity());

                response = service
                        .type(MediaType.APPLICATION_JSON)
                        .post(Transaccion.class, request);

            } catch (
                    Exception e
                    )

            {
                e.printStackTrace();
            }

            if (response != null)

            {
                System.out.println("id:" + response.getIdTransaccion());
            }


        } catch (Exception error) {
            resultado.setEstatusTrx("ERROR");
            resultado.setMensajeTrx("Transaccion NO pudo ser procesada, servidor destino no existe" + error);
            System.out.println("usuario no existe " + error);
        }
        return resultado;
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
