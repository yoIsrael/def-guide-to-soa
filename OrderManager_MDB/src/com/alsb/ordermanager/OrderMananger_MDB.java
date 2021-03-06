package com.alsb.ordermanager;

import javax.ejb.MessageDrivenBean;
import javax.jms.MessageListener;
import javax.jms.Message;
import javax.jms.TextMessage;
import weblogic.ejb.GenericMessageDrivenBean;
import weblogic.ejbgen.MessageDriven;
import java.util.Date;
import java.util.Enumeration;
import javax.jms.JMSException;

/**
 * GenericMessageDrivenBean subclass automatically generated by Workshop.
 *
 * Please complete the onMessage method and review the MessageDriven annotation
 * to ensure that the settings match your bean's intended use.
 */
@MessageDriven(ejbName = "OrderMananger_MDB", 
		destinationJndiName = "jms.WebServiceQueue", 
		destinationType = "javax.jms.Queue")
public class OrderMananger_MDB extends GenericMessageDrivenBean implements
		MessageDrivenBean, MessageListener {
	private static final long serialVersionUID = 1L;
	private static long thirtySeconds = 30000;

	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(Message msg) {
		Date now = new Date();
		try {
			// Send the txtMsg to the XMLBeans constructor if you
			// need to parse this message in the real world.
			TextMessage txtMsg = (TextMessage)msg;
			System.out.println("Message: " + txtMsg.getText());
			Thread.sleep(thirtySeconds);
			Enumeration x = msg.getPropertyNames();
			while(x.hasMoreElements()) {
				String propertyName = (String)x.nextElement();
				System.out.println("Found a message property: " + propertyName);
			}
		} catch(JMSException ex) {
			ex.printStackTrace();
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			now = new Date();
			System.out.println("Finished processing the async order at " + now.toLocaleString());
		}
	}
}