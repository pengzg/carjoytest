package xyz.carjoy.activemq.activemq02;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Receiver {
    public static void main(String[] args) throws Exception {
       ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory(
               ActiveMQConnectionFactory.DEFAULT_USER,
               ActiveMQConnectionFactory.DEFAULT_PASSWORD,
               "tcp://42.192.16.23:61616");

       activeMQConnectionFactory.setTrustAllPackages(true);
       Connection connection =  activeMQConnectionFactory.createConnection();
       connection.start();
       Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

       Destination queue = session.createTopic("testTopic");


       MessageConsumer consumer = session.createConsumer(queue);
       consumer.setMessageListener(new MyListener());
        // 实现监听
//       consumer.setMessageListener(new MessageListener() {
//           @Override
//           public void onMessage(Message message) {
//               // todo
//           }
//       });

//       while (true) {
//           TextMessage message =   (TextMessage)consumer.receive();
//           Thread.sleep(50);
//           System.out.println(message.getText());
//       }
       

    }
}
