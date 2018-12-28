package bars.login.client.ronelle.s.nolasco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("bars.login.client.ronelle.s.nolasco")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

