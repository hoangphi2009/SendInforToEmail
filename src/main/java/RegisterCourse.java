import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

@WebServlet(name ="RegisterCourse")
public class RegisterCourse extends HttpServlet {


    final String from = "hoangphi2913@gmail.com";
    final String password = "karthlgmfstrbqsm";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();


        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String field = request.getParameter("field");
        String[] subjects = request.getParameterValues("subject");
        String interestField = request.getParameter("interestField");


        StringBuilder subjectsBuilder = new StringBuilder();
        if (subjects != null) {
            for (String subject : subjects) {
                subjectsBuilder.append(subject).append(", ");
            }
        }
        String courseResult = subjectsBuilder.toString();


        String messageContent = "ID: " + id + "\n" +
            "Name: " + name + "\n" +
            "Email: " + email + "\n" +
            "Gender: " + gender + "\n" +
            "Field of Study: " + field + "\n" +
            "Course List: " + courseResult + "\n" +
            "Interesting Field: " + interestField;

        try {

            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");


            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("phido76792@gmail.com"));
            message.setSubject("Registration Details");
            message.setText(messageContent);


            Transport.send(message);


            out.println("Registration Successful. Your details have been sent to your email.");

        } catch (MessagingException e) {
            e.printStackTrace();
            out.println("Error occurred while sending email. Please try again later.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
