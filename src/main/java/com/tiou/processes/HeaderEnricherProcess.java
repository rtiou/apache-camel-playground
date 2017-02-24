package com.tiou.processes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by ronaldo on 24/02/2017.
 */
@Component
public class HeaderEnricherProcess implements Processor {
    private String arg1 = "restlet:http://camel.apache.org/recipient-list.html";
    private String arg2 = "restlet:http://camel.apache.org/simple.html";
    private String arg3 = "restlet:http://camel.apache.org/testing.html";
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("endpointList", arg1 + "," + arg2 + "," + arg3);
    }
}
