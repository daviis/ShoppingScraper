package email;

import java.util.*;
import search.Post;
import javax.mail.*;
import javax.mail.internet.*;



public class Email {
	private Session session;
	private String msgTitle;
	private ArrayList<Post> posts;
	private String msgRecever;
	private String errorMsg;
	
	//email if it broke
	public Email(String title, String rec, String error){
		msgTitle = title;
		msgRecever = rec;
		errorMsg = error;
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		this.session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("isaacspl","Gatekeeper");
					}
				}
		);
		
	}
	
	
	//email to make if things went well
	public Email(String title, String rec, ArrayList<Post> messageText){
		msgTitle = title;
		posts = messageText;
		msgRecever = rec;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		this.session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("isaacthrow99","justathrow");
				}
			});
 
		
	}
	
	public void send(){
		try {
			 
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(msgRecever));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("isaacthrow99@gmail.com"));
			message.setSubject(msgTitle);
			String text = "";
			for(Post link : posts){
				text += link.toString() +"\n\n";
			}
			message.setText(text);
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public void sysPrint(){
		for(Post p : posts){
			System.out.println(p.toString());
		}
	
	}
	
	public void errorSend(){
		try{
			Message message = new MimeMessage(this.session);
			message.setFrom(new InternetAddress(msgRecever));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msgRecever));
			message.setSubject(msgTitle);
			message.setText(errorMsg);
	
			Transport.send(message);
	
			System.out.println("Done");
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	
	}

}
