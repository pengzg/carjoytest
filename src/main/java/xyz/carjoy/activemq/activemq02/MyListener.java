package main.java.xyz.carjoy.activemq.activemq02;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MyListener implements MessageListener{
    @Override
    public void onMessage(Message message){
        TextMessage textMessage = (TextMessage)message;
        try {
            System.out.println(textMessage.getText()+"mylistener");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
