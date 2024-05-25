package co.ucentral.sistemas.gestionCitasBancarias.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("ssntiagocastillo@gmail.com");
        mailSender.setPassword("ngiw vnna iurm aqnd");

        // Configure additional properties if needed
        /*Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");*/

        mailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");


        return mailSender;
    }
}
