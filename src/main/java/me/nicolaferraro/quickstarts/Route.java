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
                .log("${body} pushed to the async queue");

        from("direct:mail")
                .id("mail")
                .log("${body} sent via mail");

        from("direct:file")
                .id("file")
                .log("${body} written to a file");


    }
}
