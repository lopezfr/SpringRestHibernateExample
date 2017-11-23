package com.umg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by carloscubur on 16/11/17.
 */

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUsuario;

    @Column(name = "nombreUsuario")
    String nombreUsuario;

    @Column(name = "coins")
    int coins;

    public Usuario() {
        super();
    }

    public Usuario(int idUsuario,String nombreUsuario, int coins) {
        super();
        this.idUsuario=idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.coins = coins;
    }

    public int getidUsuario() {
        return idUsuario;
    }

    public void setidUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getnombreUsuario() {
        return nombreUsuario;
    }

    public void setnombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
