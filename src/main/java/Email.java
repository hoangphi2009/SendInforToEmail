import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public static void main(String[] args) {
        final String from = "hoangphi2913@gmail.com";
        final String password = "karthlgmfstrbqsm";

        Properties pros = new Properties();
        pros.put("mail.smtp.host","smtp.gmail.com");
        pros.put("mail.smtp.port","587"); // Port 587 for TLS
        pros.put("mail.smtp.auth","true");
        pros.put("mail.smtp.starttls.enable","true");

        //create Auth
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password);
            }
        };

        //create session
        Session session = Session.getInstance(pros,auth);

        //send email
        final String to ="phido76792@gmail.com";
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type","text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setSubject("Your Information");
            msg.setText("This is the message body"); // Set your email content here
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to)); // Add recipient
            Transport.send(msg);
            System.out.println("Email sent successfully.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
