package at.qe.sepm.skeleton.services;

import at.qe.sepm.skeleton.model.Reservation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import at.qe.sepm.skeleton.model.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
@Scope("application")
public class EmailService {

    private static final String senderEmail = "sepmsender@gmail.com";
    private static final String senderPassword = "sender123";

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

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

    //Notifies the user about his reservation
    public void reservationCreatedNotification(Reservation reservation, Collection<StockItem> selectedItems) throws MessagingException {
        String stockItemsString ="";
        for(StockItem item: selectedItems){
            stockItemsString = item.getLabItem().getItemName() + ", ";
        }

        String title = "Reservation created!";
        String html =
                "<h1>We created a reservation for you</h1>" +
                        "You reserved "+ selectedItems.size() + " items:<br>" +
                        stockItemsString +
                "<br>Please bring it back before the reservation expires on: " + reservation.getReturnableDate() +
                "<br><br>Kind regards,<br> Group 4";

        if (reservation.getUser().getEmail() == null){
            auditLogService.reservationUserEmailInvalid(reservation);
            return;
        }
        System.out.println("Sending email to " + reservation.getUser().getEmail());

        sendNotificationEmail(reservation.getUser().getEmail(), title, html);
        auditLogService.reservationCreatedEmailLog(reservation);
    }

    //daily 06:00 AM, checks for expired notifications and mails the user + admin
    @Scheduled(cron = "0 0 6 1/1 * *")
    public void reservationExpiredNotification() throws MessagingException {
        Collection<Reservation> reservations = reservationService.loadActive();
        String title = "Reservation expired!";
        String title_admin = "User-Reservation expired!";

        for(Reservation res: reservations){
            String html =
                    "<h1>Please return your reserved item!</h1><br>" +
                            "The item: " + res.getItem().getLabItem().getItemName() +
                            "must be returned as soon as possible." +
                            "<br>Reservation due date: " + res.getReturnableDate() +
                            "<br><br>Kind regards, <br>Group 4";

            String html_admin =
                    "<h1>A users reservation expired!</h1><br>" +
                            "The item: " + res.getItem().getLabItem().getItemName() +
                            "from user" + res.getUser().getUsername() +
                            "with:" +
                            "<br>Reservation due date: " + res.getReturnableDate() +
                            "expired" +
                            "<br><br>Kind regards, <br>you";

            if(res.getReturnableDate().after(new Date())){

                sendNotificationEmail(res.getUser().getEmail(), title, html);
                sendNotificationEmail(userService.loadUser("admin").getEmail(), title_admin, html_admin);

                auditLogService.reservationExpired(res);
            }
        }
    }

    //every 4 hours, checks for expiring reservations and notifies user
    @Scheduled(cron = "0 0 0/4 1/1 * *")
    public void reservationExpiresSoonNotification() throws MessagingException {
        Collection<Reservation> reservations = reservationService.loadActive();
        String title = "Reservation expires soon!";

        for(Reservation res: reservations){
            String html =
                    "<h1>Please return your reserved items on time!</h1><br>" +
                            "The item: " + res.getItem().getLabItem().getItemName() +
                            "must be returned until reservation due date: " + res.getReturnableDate() +
                            "<br><br>Kind regards, <br>Group 4";

            //Calulate time difference between returnableDate and current time
            Date date = new Date();
            long diff = res.getReturnableDate().getTime() - date.getTime();
            diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

            if(!res.isNotified() && (diff<=1)){

                sendNotificationEmail(res.getUser().getEmail(), title, html);
                res.setNotified(true);
                auditLogService.reservationExpiresSoon(res);
            }
        }
    }

    private void sendNotificationEmail(String email, String title, String html) throws MessagingException{
        Session session = createSession();
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, email, title, html);
        Transport.send(message);
    }

	public void sendPassword(String pass) {
		// TODO send password via email
		
	}
}