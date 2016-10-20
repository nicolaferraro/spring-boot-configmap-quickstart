package me.nicolaferraro.quickstarts;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Route extends RouteBuilder {

    @Autowired
    private RouteConfig config;

    @Override
    public void configure() throws Exception {

        from("timer://tick?period=3000")
                .id("generation")
                .transform().simple("message-${header.CamelTimerCounter}")
                .recipientList().method(config, "getRecipients");


        from("direct:async-queue")
                .id("async-queue")
                .log("Message pushed to the async queue: ${body}");

        from("direct:mail")
                .id("mail")
                .log("Message sent via mail: ${body}");

        from("direct:file")
                .id("file")
                .log("Message written to a file: ${body}");


    }
}
