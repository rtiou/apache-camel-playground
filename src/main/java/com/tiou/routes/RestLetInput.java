package com.tiou.routes;

import com.tiou.processes.HeaderEnricherProcess;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ronaldo on 23/02/2017.
 */
@ConfigurationProperties(prefix = "restlet")
@Component
public class RestLetInput extends RouteBuilder {
    private String address = "http://localhost:9999";

    @Autowired
    private HeaderEnricherProcess process;

    @Override
    public void configure() throws Exception {
        errorHandler(deadLetterChannel("mock:error"));

        String restlet = "restlet:" + address + "/input";

        from(restlet)
                .id("restLet Camel Route")
                .log(LoggingLevel.DEBUG, "Input")
                .process(process)
                .to("seda:recipientListSeda");

        from(restlet+"?restletMethod=POST")
                .id("Input using POST method")
                .to("log:local");
    }
}
