package guide.triple.mileage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class MileageWebSvrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MileageWebSvrApplication.class, args);
	}

}
