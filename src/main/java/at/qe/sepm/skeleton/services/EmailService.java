package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.Reservation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;

@Service
@Scope("application")
public class EmailService {

    private static final String senderEmail = "sepmsender@gmail.com";
    private static final String senderPassword = "sender123";

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private ReservationService reservationService;

    private void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private Session createSession() {
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
                "<br><br>Kind regards,<br> Group 4";

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

    //daily 06:00 AM
    @Scheduled(cron = "0 0 06 00 * ?")
    public void reservationExpiredNotification() throws MessagingException {
        Collection<Reservation> reservations = reservationService.loadActive();

        for(Reservation res: reservations){
            String title = "Reservation expired!";
            String html =
                    "<h1>Please return your reserved item!</h1><br>" +
                            "The item: " + res.getItem().getLabItem().getItemName() +
                            "must be returned as soon as possible." +
                            "<br>Reservation due date: " + res.getReturnableDate() +
                            "<br><br>Kind regards, <br>Group 4";

            if(res.getReturnableDate().after(new Date())){
                Session session = createSession();
                MimeMessage message = new MimeMessage(session);
                prepareEmailMessage(message, res.getUser().getEmail(), title, html);
                Transport.send(message);

                auditLogService.reservationExpired(res);
            }
        }

    }

	public void sendPassword(String pass) {
		// TODO send password via email
		
	}
}