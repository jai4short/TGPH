package com.porschePETFinder.www.emailService;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class emailBuilder {

    String msgBody = "Message Body";
    String subBody = "Subject Body";
    String toRecip = "Recipient";
    String toRecipName = "Recipient Name";
	
	public emailBuilder(){
	}
	
	public emailBuilder(String msg, String sub, String recip, String recipName){
		msgBody = msg;
		subBody = sub;
		toRecip = recip;
		toRecipName = recipName;
	}
	
	public void setMsg(String msg){
		msgBody = msg;
	}
	
	public void setSub(String sub){
		subBody = sub;
	}
	
	public void setRecip(String recip){
		toRecip = recip;
	}
	
	public void setUserName(String recipName){
		toRecipName = recipName;
	}
	
	public void buildMessage(){


        try {
        	System.out.println("Message building has begun...");
        	System.out.println("recipient: " + toRecip);
        	System.out.println("message body: " + msgBody);
        	System.out.println("recipient name: " + toRecipName);
        	Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            Message msg = new MimeMessage(session);
            //msg.setFrom(new InternetAddress("admin@tunedtrader.com", "TunedTrader.com Administrator"));
            msg.setFrom(new InternetAddress("jmcpherson1621@gmail.com", "TunedTrader.com Administrator"));
            msg.addRecipient(Message.RecipientType.TO,
            			new InternetAddress(toRecip, toRecipName));
            msg.setSubject(subBody);
            msg.setText(msgBody);
            Transport.send(msg);
            System.out.println("The message has been sent.");

        } catch (AddressException e) {
            System.out.println("Error: AddressException");
            e.printStackTrace();
        } catch (MessagingException e) {
        	System.out.println("Error: MessagingException");
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
