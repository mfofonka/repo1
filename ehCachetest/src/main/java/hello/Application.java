package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableCaching
@ImportResource(locations = { "classpath*:applicationContext.xml" })
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    // private static final Logger log =
    // LoggerFactory.getLogger(Application.class);
    //
    // @Component
    // static class Runner implements CommandLineRunner {
    // @Autowired
    // private BookRepository bookRepository;
    //
    // @Override
    // public void run(String... args) throws Exception {
    // log.info(".... Fetching books");
    // log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    // log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    // log.info("load ------->" + bookRepository.loadBooks("isbn-1234"));
    // log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    // log.info("f5 isbn-1234 " + bookRepository.updateBook("isbn-1234"));
    // log.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    // }
    // }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
