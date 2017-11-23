package com.umg.queue;

/**
 * Created by carloscubur on 16/11/17.
 */
public class EnviaMsjCola {

    public boolean enviaMensajeaMQ(String mensaje){
        boolean estatus=false;
        //Valores configurables en /resources/application.properties/
        String nombreCola = "PROYECTO.COLAMENSAJE";
        String nombreServicios = "EjemploCola";
        String serverLocation = "failover:(tcp://192.168.10.2:61616)?timeout=3000";
        String message = mensaje;
        try {
            System.out.println("Enviando mensaje....");
            QueueUtil.send(nombreCola, true, true, 0, nombreServicios, message, serverLocation);
            System.out.println("Mensaje enviado....");
            estatus=true;

        } catch (Exception e) {
            System.out.println("Error....");
            e.printStackTrace();
        }
        return estatus;
    }
}
