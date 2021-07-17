package main.java.xyz.carjoy.activemq.activemq02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://42.192.16.23:61616");

        Connection connection =  activeMQConnectionFactory.createConnection();

//        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createTopic("testTopic");


        MessageProducer producer =  session.createProducer(queue);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage("hi"+i);

            if (i % 4 == 0 )   {
                producer.setPriority(4);
            } else {

                producer.setPriority(1);
            }
//            producer.setTimeToLive(200);
            producer.send(textMessage);
//            if (i % 4 == 0 )   {
//                session.rollback();
//            }
//
//            if (i % 3 == 0 )   {
//                session.commit();
//            }
//            Thread.sleep(1000);
        }


        connection.close();





    }
}