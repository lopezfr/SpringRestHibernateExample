package com.umg.controller;

import java.util.List;
import com.google.gson.Gson;
import com.umg.model.Resultado;
import com.umg.model.TrxOperada;
import com.umg.service.ServidorDestinoService;
import com.umg.service.TransaccionService;
import com.umg.service.TrxOperadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by carloscubur on 24/11/17.
 * Este graba la trx ya operada, recibe del Demonio el post con el json
 */

@RestController
public class TrxOperadaController {

    @Autowired
    TrxOperadaService trxOperadaService;

    @Autowired
    TransaccionService transaccionService;

    @Autowired
    ServidorDestinoService servidorDestinoService;


    //con requesmapping hacemos los direccionamientos de las URL

    //Obtener un Json con todas las transacciones
    @RequestMapping(value = "/getAllTrxOperadas", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<TrxOperada> getAllTrxOperadas(){
        System.out.println("Rest devuelve todas las trx");
        List<TrxOperada> trxOperadaList = trxOperadaService.getAllTrxOperadas();
        return trxOperadaList;
    }

    //Obtener un Json segun el id que mandemos en la url
    @RequestMapping(value = "/getTrxOperada/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public TrxOperada getTrxOperadaById(@PathVariable("id") int id){return trxOperadaService.getTrxOperada(id);}

    //Crear una nueva transaccion enviandole un Json con post
    @RequestMapping(value = "/addTrxOperada", method = RequestMethod.POST, headers = "Accept=application/json")
    public Resultado addTrxOperada(@RequestBody TrxOperada trxOperada){
        //Prepara objeto a devolver con resultado
        Resultado resultado = new Resultado();
        return resultado;
    }
}
