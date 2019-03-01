package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Mason
 */
public class EmailService {

    AccountService as = new AccountService();

    public EmailService() {

    }

    public void sendMail(String email, String subject, String template, HashMap<String, String> tags) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(template)));
            String line = br.readLine();
            String body = "";

            while (line != null) {
                body += line;
                line = br.readLine();
            }

            for (String tag : tags.keySet()) {
                body = body.replace("%" + tag + "%", tags.get(tag));
            }

            sendMail(email, subject, body, true);

        } catch (Exception ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendMail(String to, String subject, String body, boolean bodyIsHTML) throws NamingException, MessagingException {
        Context env = (Context) new InitialContext().lookup("java:comp/env");
        String username = (String) env.lookup("webmail-username");
        String password = (String) env.lookup("webmail-password");

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }

        Address fromAddress = new InternetAddress(username);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);

        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public String generateCode() {
        //36 Chars
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(7);

        for (int i = 0; i < 7; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }
}
