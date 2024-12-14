package info.boaventura.scan;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigFileApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        // User home configurations
        String userHome = System.getProperty("user.home");
        Path externalConfigDir = Paths.get(userHome, ".javascan");
        Path externalConfigFile = Paths.get(externalConfigDir.toString(), "config.yaml");
        // Create directory if it does not exists
        File directory = new File(externalConfigDir.toString());
        if (!directory.exists() && !directory.mkdirs()) {
            throw new RuntimeException("Home " + externalConfigDir + " cannot be created: check access permission.");
        }
        // Check if file config exists
        File fileConfig = new File(externalConfigFile.toString());
        if (!fileConfig.exists()) {
            try {
                if (!fileConfig.createNewFile()) {
                    throw new RuntimeException("Cannot create file at " + externalConfigFile + ": check access permission.");
                }
            } catch (IOException e) {
                throw new RuntimeException("Cannot create file at " + externalConfigFile, e);
            }
        }
        // Add location to SpringBoot
        System.setProperty("spring.config.additional-location", externalConfigFile.toString());
    }

}
