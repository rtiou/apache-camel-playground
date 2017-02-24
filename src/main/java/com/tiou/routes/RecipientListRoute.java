package com.tiou.routes;

import com.tiou.aggregation.AggregationList;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ronaldo on 24/02/2017.
 */
@Component
public class RecipientListRoute extends RouteBuilder {

    @Autowired
    private AggregationList aggregation;

    @Override
    public void configure() throws Exception {
        from("direct:recipientListExample")
                .recipientList(header("endpointList").tokenize(","))
                .parallelProcessing()
                .aggregationStrategy(aggregation);
    }
}
