package activity_new.activity_new;

import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.repository.UserRepository;
import activity_new.activity_new.service.impl.EmailService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class CronScheduler {

    private final EmailService emailService;
    private final UserRepository userRepository;


    private final Logger LOGGER = LoggerFactory.getLogger(CronScheduler.class);

    public CronScheduler(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }


    @Scheduled(cron = "${schedulers.cron}")
    public void sendEmail() {
        List<UserEntity> emails = userRepository.findAll();

        for (UserEntity user : emails) {
            emailService.sendMessage(user.getEmail(), user.getUsername());
        }

        LOGGER.info("Emails was successfully send {}", LocalDate.now());
    }
}
