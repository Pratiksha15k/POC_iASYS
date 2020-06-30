package brix.core.email.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/******
 * Application used for getting details used for sending email
 * It reads json and returns as it is.
 * @author pratiksha.datir
 *
 */
@SpringBootApplication
public class GetUserDetailsApplication {
	public static void main(String[] args) {
		SpringApplication.run(GetUserDetailsApplication.class, args);
	}
}
