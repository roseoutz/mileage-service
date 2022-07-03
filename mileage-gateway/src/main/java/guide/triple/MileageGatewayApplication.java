package guide.triple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MileageGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MileageGatewayApplication.class, args);
    }

}
