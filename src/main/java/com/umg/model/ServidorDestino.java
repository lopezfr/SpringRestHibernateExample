package com.umg.model;

import javax.persistence.*;
/**
 * Created by carloscubur on 16/11/17.
 * en table name se debe poner exactamente como se llama en mysql, sql, oracle si no no lo reconoce
 */

@Entity
@Table(name = "servidordestino")
public class ServidorDestino {

    @Id
    @Column(name = "idPlatformDestiny")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPlatformDestiny;

    @Column(name = "servername")
    String servername;

    @Column(name = "serverip")
    String serverip;

    @Column(name = "serverport")
    String serverport;

    public ServidorDestino() {
        super();
    }

    public ServidorDestino(int idPlatformOrigin,String servername, String serverip, String serverport) {
        super();
        this.idPlatformDestiny = idPlatformOrigin;
        this.servername = servername;
        this.serverip = serverip;
        this.serverport = serverport;
    }

    public int getIdPlatformDestiny() {
        return idPlatformDestiny;
    }

    public void setIdPlatformDestiny(int idPlatformDestiny) {
        this.idPlatformDestiny = idPlatformDestiny;
    }

    public String getServername() {
        return servername;
    }

    public void setServername(String servername) {
        this.servername = servername;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    public String getServerport() {
        return serverport;
    }

    public void setServerport(String serverport) {
        this.serverport = serverport;
    }
}
