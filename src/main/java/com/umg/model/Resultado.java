package com.umg.model;

import java.io.Serializable;

/**
 * Created by carloscubur on 23/11/17.
 */
public class Resultado implements Serializable{

    private String estatusTrx;
    private String mensajeTrx;

    public Resultado() {
    }

    public Resultado(String estatusTrx, String mensajeTrx) {
        this.estatusTrx = estatusTrx;
        this.mensajeTrx = mensajeTrx;
    }

    public String getEstatusTrx() {
        return estatusTrx;
    }

    public void setEstatusTrx(String estatusTrx) {
        this.estatusTrx = estatusTrx;
    }

    public String getMensajeTrx() {
        return mensajeTrx;
    }

    public void setMensajeTrx(String mensajeTrx) {
        this.mensajeTrx = mensajeTrx;
    }
}
