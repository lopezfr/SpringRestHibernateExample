package com.umg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by carloscubur on 15/11/17.
 */
@Entity
@Table(name="transaccion")
public class Transaccion {

    @Id
    @Column(name="idTransaccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTransaccion;

    @Column(name = "idUsuario")
    int idUsuario;

    @Column(name = "idCoin")
    int idCoin;

    @Column(name = "idPlatformOrigin")
    int idPlatformOrigin;

    @Column(name = "idPlatformDestiny")
    int idPlatformDestiny;

    @Column(name = "idProduct")
    int idProduct;

    @Column(name = "Mount")
    BigDecimal Mount;

    @Column(name = "Quantity")
    int Quantity;

    public Transaccion() { super();
    }

    public Transaccion(int idTransaccion, int idUsuario, int idCoin, int idPlatformOrigin, int idPlatformDestiny, int idProduct, BigDecimal Mount, int Quantity) {
        super();
        this.idTransaccion=idTransaccion;
        this.idUsuario=idUsuario;
        this.idCoin =idCoin;
        this.idPlatformOrigin = idPlatformOrigin;
        this.idPlatformDestiny = idPlatformDestiny;
        this.idProduct = idProduct;
        this.Mount = Mount;
        this.Quantity = Quantity;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(int idCoin) {
        this.idCoin = idCoin;
    }

    public int getIdPlatformOrigin() {
        return idPlatformOrigin;
    }

    public void setIdPlatformOrigin(int idPlatformOrigin) {
        this.idPlatformOrigin = idPlatformOrigin;
    }

    public int getIdPlatformDestiny() {
        return idPlatformDestiny;
    }

    public void setIdPlatformDestiny(int idPlatformDestiny) {
        this.idPlatformDestiny = idPlatformDestiny;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public BigDecimal getMount() {
        return Mount;
    }

    public void setMount(BigDecimal mount) {
        Mount = mount;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
