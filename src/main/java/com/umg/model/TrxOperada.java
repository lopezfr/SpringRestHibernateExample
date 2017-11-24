package com.umg.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by carloscubur on 23/11/17.
 * Esta tabla la graba el servidor que recibe el JSON
 */

@Entity
@Table(name = "trxoperada")
public class TrxOperada implements Serializable {


    @Id
    @Column(name="idTrxOperada")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTrxOperada;

    @Column(name = "idTransaccion")
    int idTransaccion;

    @Column(name = "serverName")
    String serverName;

    @Column(name = "serverIp")
    String serverIp;

    public TrxOperada() {
        super();
    }

    public TrxOperada(int idTrxOperada,int idTransaccion, String serverName, String serverIp) {
        super();
        this.idTrxOperada = idTrxOperada;
        this.idTransaccion = idTransaccion;
        this.serverName = serverName;
        this.serverIp = serverIp;
    }

    public int getIdTrxOperada() {
        return idTrxOperada;
    }

    public void setIdTrxOperada(int idTrxOperada) {
        this.idTrxOperada = idTrxOperada;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
