package com.surittec.avaliacaodesenvolvedor;

import com.surittec.avaliacaodesenvolvedor.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class AvaliacaoDesenvolvedorApplication {
  private static final Logger log = LoggerFactory.getLogger(AvaliacaoDesenvolvedorApplication.class);

    public static void main(String[] args) {
      final SpringApplication application = new SpringApplication(AvaliacaoDesenvolvedorApplication.class);
      final Environment env = application.run(args).getEnvironment();

      String hostAddress;
      try {
        hostAddress = InetAddress.getLocalHost().getHostAddress();
      } catch (UnknownHostException e) {
        log.error("Unable to get LocalHostAddress", e);
        hostAddress = "Unable to get LocalHostAddress";
      }

      log.info("\n----------------------------------------------------------" +
          "\n\t" + "Application '{}' is running! " +
          "Access URLs:\n\t " +
          "Local: \t{}://localhost:{}\n\t " +
          "External: \t{}://{}:{}\n\t " +
          "Profile: \t{}\n" +
          "----------------------------------------------------------",
        env.getProperty("spring.application.name"),
        "http",
        env.getProperty("server.port"),
        "http",
        hostAddress,
        env.getProperty("server.port"),
        env.getActiveProfiles());

    }

}
