package me.nicolaferraro.quickstarts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "route")
public class RouteConfig {

    /**
     * The recipient endpoint.
     */
    private String recipients;

    public RouteConfig() {
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }
}
