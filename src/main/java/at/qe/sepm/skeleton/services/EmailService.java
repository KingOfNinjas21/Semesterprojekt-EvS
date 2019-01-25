package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private static final String senderEmail = "sepmsender@gmail.com";
    private static final String senderPassword = "sender123";

    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }

    public static void reservationExpiredNotification(User user, Reservation reservation) throws MessagingException {
        String title = "Reservierung abgelaufen!";
        String html =
                "<h1>Laborgeräte zurückbringen!</h1>" +
                        "<p>Bringen Sie die Geräte der Reservierung(" + reservation.getId() + ") vom </p>" +
                        reservation.getReservationDate() +
                        " unverzüglich zurück!</p>";

        System.out.println("Sending email to " + user.getEmail());

        Session session = createSession();

        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, user.getEmail(), title, html);

        Transport.send(message);
        System.out.println("Done");
    }

    public static void reservationExpiredNotification(String email) throws MessagingException {
        String title = "Reservierung abgelaufen!";
        String html =
                "<h1>Laborgeräte zurückbringen!</h1>";


        System.out.println("Sending email to " + email);

        Session session = createSession();

        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, email, title, html);

        Transport.send(message);
        System.out.println("Done");
    }

	public void sendPassword(String pass) {
		// TODO send password via email
		
	}
}