package fofonka.marcos.propertyReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableScheduling
public class ExecApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ExecApplication.class, args);
	}
}
