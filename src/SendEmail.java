
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;


public class SendEmail
{
	   String host, port, emailid,username, password;
	    Properties props = System.getProperties();
	    Session l_session = null;

	    public SendEmail(String toEmail, String subject, String msg) {
	        host = "smtp.mail.yahoo.com";
	        port = "587";
	        emailid = "companiesrl@yahoo.com";
	        username = "companiesrl";
	        password = "Catel000";

	        emailSettings();
	        createSession();
	        sendMessage("companiesrl@yahoo.com", toEmail,subject,msg);
	    }

	    public void emailSettings() {
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.debug", "false");
	        props.put("mail.smtp.port", port);
	        props.put("mail.smtp.ssl.trust", host);
	        props.put("mail.smtp.starttls.enable", "true");

	    }

	    public void createSession() {

	        l_session = Session.getInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication("companiesrl@yahoo.com", password);
	                    }
	                });


	    }

	    public boolean sendMessage(String emailFromUser, String toEmail, String subject, String msg) {
	        try {
	            MimeMessage message = new MimeMessage(l_session);
	            emailid = emailFromUser;       
	            message.setFrom(new InternetAddress(this.emailid));
	            
	            
	            MimeBodyPart messageBodyPart =   
	              new MimeBodyPart();  
 
	            messageBodyPart.setText(msg);  
	            Multipart multipart = new MimeMultipart();  
	            multipart.addBodyPart(messageBodyPart);  

	            String fileAttachment = "C:\\Users\\Oana\\Desktop\\here.pdf"; 
	            messageBodyPart = new MimeBodyPart();  
	            FileDataSource source =   
	              new FileDataSource(fileAttachment);  
	            messageBodyPart.setDataHandler(  
	              new DataHandler(source));  
	            messageBodyPart.setFileName(fileAttachment);  
	            multipart.addBodyPart(messageBodyPart);  

	            message.setContent(multipart);  
	          
	            
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	            message.setSubject(subject);

	            Transport.send(message);
	            System.out.println("Message Sent");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return true;
	    }
}