/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author root
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/ictqueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class QueueBean1 implements MessageListener {
    
    public QueueBean1() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        if(message instanceof TextMessage)
        {
            try {
                
                String msg = ((TextMessage)message).getText();
            
                System.out.println("QueueBean1 recieved  : "+msg);
                
                
                
            } catch (JMSException ex) {
                Logger.getLogger(QueueBean1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
