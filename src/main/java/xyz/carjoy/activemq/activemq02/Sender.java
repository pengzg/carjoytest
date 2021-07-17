package main.java.xyz.carjoy.activemq.activemq02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://42.192.16.23:61616");
        activeMQConnectionFactory.setTrustAllPackages(true);
        Connection connection =  activeMQConnectionFactory.createConnection();
        // 添加信任列表



//        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createTopic("testTopic");


        MessageProducer producer =  session.createProducer(queue);

        // 字节流 图 文件小的
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.writeChar('A');
        producer.send(bytesMessage);
        // 对象
//        Girl girl = new Girl("Lucy", 28, 20);
//
//        ObjectMessage objectMessage = session.createObjectMessage(girl);
//        producer.send(objectMessage);
        // 文本
//        for (int i = 0; i < 100; i++) {
//            TextMessage textMessage = session.createTextMessage("hi"+i);
//
//            if (i % 4 == 0 )   {
//                producer.setPriority(4);
//            } else {
//
//                producer.setPriority(1);
//            }
//            producer.send(textMessage);
//
//        }


        connection.close();





    }
}