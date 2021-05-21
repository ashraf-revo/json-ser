package org.revo.jsonser.config;

import org.revo.jsonser.domain.Email;
import org.revo.jsonser.domain.Phone;
import org.revo.jsonser.domain.Status;
import org.revo.jsonser.domain.User;
import org.revo.jsonser.repository.EmailRepository;
import org.revo.jsonser.repository.PhoneRepository;
import org.revo.jsonser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class InitConfig {
    @Bean
    CommandLineRunner runner(UserRepository userService, PhoneRepository phoneService, EmailRepository emailService) {
        return (args) -> {
            User savedUser = userService.save(new User(null, "ashraf", Status.ACTIVE, new ArrayList<>(), new HashSet<>(), null));
            Phone savedPhone1 = phoneService.save(new Phone(null, "p1", savedUser));
            Phone savedPhone2 = phoneService.save(new Phone(null, "p2", savedUser));

            Email savedEmail1 = emailService.save(new Email(null, "e1", savedUser));
            Email savedEmail2 = emailService.save(new Email(null, "e2", savedUser));

            savedUser.setPhones(Arrays.asList(savedPhone1, savedPhone2));
            savedUser.setEmails(new HashSet<>(Arrays.asList(savedEmail1, savedEmail2)));
            savedUser = userService.save(savedUser);
        };
    }
}
