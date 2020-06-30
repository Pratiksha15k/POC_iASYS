import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendWhatsAppMessage {
   // Replace these placeholders with your Account Sid and Auth Token
   public static final String ACCOUNT_SID = "ACf02073a94b19bd692f940dd92b85845a";
   public static final String AUTH_TOKEN = "1df287691814884f981f7c55ed89cb5c";

   public static void main(String[] args) throws Exception{
       Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
       Message message = Message.creator(
               new com.twilio.type.PhoneNumber("whatsapp:+917057958566"),
               new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
               "Hello from your friendly neighborhood Java application!")
           .create();
       //System.out.println("message"+message);
   }
}