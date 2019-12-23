package mx.com.goh.security.keyprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "mx.com.goh.security.keyprovider" })
@Configuration
@EnableAutoConfiguration
public class McKeyproviderServiceApplication implements CommandLineRunner {
    
    @Value("${spring.profiles}")
    private String activeProfile;
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println(String.format("The [%s] application has been built in the environment [%s].",
                applicationName, activeProfile));
    }

    public static void main(String[] args) {
        SpringApplication.run(McKeyproviderServiceApplication.class, args);
    }

}
