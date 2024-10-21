package info.boaventura.scan;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

import java.io.File;

public class ConfigFileApplicationListener implements ApplicationListener<ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        // User home configurations
        String userHome = System.getProperty("user.home");
        String externalConfigDir = userHome + "/.javascan";
        String externalConfigFile = externalConfigDir + "/config.yaml";
        // Create directory if it does not exists
        File directory = new File(externalConfigDir);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new RuntimeException("Home " + externalConfigDir + " cannot be created: check access permission.");
            }
        }
        System.setProperty("spring.config.additional-location", externalConfigFile);
    }

}
