package main.java.xyz.carjoy.activemq.activemq02;

import javax.jms.*;


public class MyListener implements MessageListener{
    @Override
    public void onMessage(Message message){

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)message;
            try {
                System.out.println(textMessage.getText()+"mylistener");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if(message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage)message;
            try {
                Girl object = (Girl) objectMessage.getObject();
                System.out.println(object.getName());
                System.out.println(object.getAge());
                System.out.println(object.getPrice());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else if(message instanceof BytesMessage) {
            BytesMessage bytesMessage = (BytesMessage)message;
            try {

                System.out.println(bytesMessage.readChar());

            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
