package main.java.xyz.carjoy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Receiver {
    public static void main(String[] args) throws Exception {
       ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory(
               ActiveMQConnectionFactory.DEFAULT_USER,
               ActiveMQConnectionFactory.DEFAULT_PASSWORD,
               "tcp://42.192.16.23:61616");

       Connection connection =  activeMQConnectionFactory.createConnection();
       connection.start();
       Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

       Destination queue = session.createQueue("test01");


       MessageConsumer consumer = session.createConsumer(queue);

       while (true) {
           TextMessage message =   (TextMessage)consumer.receive();
           System.out.println(message.toString());
       }


    }
}
