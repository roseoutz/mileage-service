package guide.triple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEurekaClient
@SpringBootApplication
public class MileageQueryWebSvrApplication {

    public static void main(String[] args) {
        SpringApplication.run(MileageQueryWebSvrApplication.class, args);
    }

}
