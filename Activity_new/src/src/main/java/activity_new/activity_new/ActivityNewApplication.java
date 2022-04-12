package activity_new.activity_new;

import activity_new.activity_new.service.impl.EmailService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.logging.Logger;


@SpringBootApplication
@EnableScheduling
public class ActivityNewApplication {


    @Autowired
    private final EmailService emailService;
    private final Logger LOGGER = Logger.getLogger(String.valueOf(ActivityNewApplication.class));

    public ActivityNewApplication(EmailService emailService) {
        this.emailService = emailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ActivityNewApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    private void sendSimpleMessage(){
        emailService.sendSimpleMessage("yakimov099@gmail.com"
        , "Application - Activity is ready to use -"
        ,"-- Activity is ready to use --" + LocalDateTime.now());

        LOGGER.info("The task was successfully completed at: "+ LocalDateTime.now());
    }

}
