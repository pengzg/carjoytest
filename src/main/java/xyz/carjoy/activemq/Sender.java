package main.java.xyz.carjoy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Sender {
    public static void main(String[] args) throws Exception {
       ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory(
               ActiveMQConnectionFactory.DEFAULT_USER,
               ActiveMQConnectionFactory.DEFAULT_PASSWORD,
               "tcp://42.192.16.23:61616");

        Connection connection =  activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test01");


        MessageProducer producer =  session.createProducer(queue);


        TextMessage textMessage = session.createTextMessage("hi");

        producer.send(textMessage);

        connection.close();





    }
}
