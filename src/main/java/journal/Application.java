package journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public final static String TITLE = "Mood Journal";
    public final static String VERSION = "0.4.0 Alpha";
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(Application.class, args);

    }
}
