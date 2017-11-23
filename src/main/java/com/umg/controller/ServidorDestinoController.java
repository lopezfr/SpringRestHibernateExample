package com.umg.controller;

import java.util.List;

import com.google.gson.Gson;
import com.umg.model.ServidorDestino;
import com.umg.service.ServidorDestinoService;
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
public class ServidorDestinoController {

    @Autowired
    ServidorDestinoService servidorDestinoService;

    //con requesmapping hacemos los direccionamientos de las URL

    //Obtener un Json con todos los servidores
    @RequestMapping(value = "/getAllServidorDestino", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<ServidorDestino> getServidores(){
        List<ServidorDestino> servidorDestinoList = servidorDestinoService.getAllServidorDestino();
        return servidorDestinoList;
    }
    //Obtener un Json segun el id que mandemos en la url
    @RequestMapping(value = "/getServidorDestino/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ServidorDestino getServidorById(@PathVariable int id){
        return servidorDestinoService.getServidorDestino(id);
    }
}
