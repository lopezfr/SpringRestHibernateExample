package com.umg.queue;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.activemq.pool.PooledConnectionFactory;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
/**
 * Created by carloscubur on 16/11/17.
 */
public class QueueUtil {
    static final Map<String, PooledConnectionFactory> connPoolCache = Collections.synchronizedMap(new WeakHashMap<String, PooledConnectionFactory>());
    private final static Object locker = null;
    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * Obtiene un nuevo consumer de activeMQ
     *
     * @param serverUrl direccion de servidor
     * @param queueName nombre de la cola
     * @param serviceName nombre del servicio que lo solicita
     * @return
     * @throws //QueueException
     */

    public static void send(String queueName, boolean persisted, boolean transacted, int priority, String serviceName, String textMess, String serverUrl) {
        System.out.println("QueueUtil->send message");
        //Connection connection = pooledConnectionFactory.createConnection();
        try {
            System.out.println("QueueUtil->send->try");
            Connection connection = getConnectionFromPool(serverUrl);
            if (connection.getClientID() == null) {
                System.out.println("QueueUtil->send->try->if:clienID");
                String clienId = String.format("%s-%s", serviceName, UUID.randomUUID());
                System.out.println("QueueUtil->send->try->if: setiando el id a la conexion");
                connection.setClientID(clienId);
            }
            System.out.println("QueueUtil->send->try:iniciando conexion");
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            ActiveMQQueue mQueue = new ActiveMQQueue(queueName);

            MessageProducer producer = session.createProducer(mQueue);
            producer.setDeliveryMode(persisted ? DeliveryMode.PERSISTENT : DeliveryMode.NON_PERSISTENT);
            producer.setPriority(priority);

            ActiveMQTextMessage message = new ActiveMQTextMessage();
            message.setText(textMess);

            //logger.debug(String.format("Sending message: priority[%d] message: %s",message.getJMSPriority(), message.getText()));
            producer.send(message);

            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static Connection getConnectionFromPool(String serverUrl) throws Exception {
        PooledConnectionFactory factory = null;
        try {
            boolean found = true;
            if (!connPoolCache.containsKey(serverUrl)) {
//                 synchronized (locker){
                try {
                    lock.lock();
                    if (!connPoolCache.containsKey(serverUrl)) {
                        found = false;
                    }
//                 }
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
            }

            if (found) {
                factory = connPoolCache.get(serverUrl);
            } else {
//                synchronized (locker){
                try {
                    lock.lock();
                    ConnectionFactory jmsConnectionFactory = new ActiveMQConnectionFactory(serverUrl);
                    factory = new PooledConnectionFactory();
                    factory.setConnectionFactory(jmsConnectionFactory);
                    factory.setMaxConnections(200);  	//TO DO: get from a propertyFile
                    factory.setIdleTimeout(1000 * 1000);  //TO DO:  get from a propertyFile

                    connPoolCache.put(serverUrl, factory);
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
//                }
            }
            return factory.createConnection();
        } catch (JMSException e) {
            throw new Exception("Error obteniendo conexion del pool", e);
        }
    }
}
