package brix.core.send.email;

/**********
 * @author pratiksha.datir
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
@Component(value = "brix.core.send.email")
public class SendEmailUtilityApplication /* implements CommandLineRunner */ {
	public static void main(String[] args) {
		SpringApplication.run(SendEmailUtilityApplication.class, args);
	}
}