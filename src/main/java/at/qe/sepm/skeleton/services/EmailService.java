package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.AuditLog;
import at.qe.sepm.skeleton.model.Reservation;
import at.qe.sepm.skeleton.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    private static final String senderEmail = "sepmsender@gmail.com";
    private static final String senderPassword = "sender123";

    @Autowired
    private AuditLogService auditLogService;

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

    public void reservationCreatedNotification(Reservation reservation) throws MessagingException {
        String title = "Reservation created!";
        String html =
                "<h1>We created a reservation for you</h1>" +
                        "This is the item: "+ reservation.getItem().getLabItem().getItemName() +
                "<br>Please bring it back before the reservation expires on: " + reservation.getReturnableDate() +
                "<br>Kind regards,<br> Group 4";

        if (reservation.getUser().getEmail() == null){
            auditLogService.reservationUserEmailInvalid(reservation);
            return;
        }

        System.out.println("Sending email to " + reservation.getUser().getEmail());

        Session session = createSession();

        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, reservation.getUser().getEmail(), title, html);

        Transport.send(message);
        auditLogService.reservationCreatedEmailLog(reservation);
    }

    public void reservationExpiredNotification(String email) throws MessagingException {
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