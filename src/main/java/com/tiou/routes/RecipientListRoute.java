package com.tiou.routes;

import com.tiou.aggregation.AggregationList;
import com.tiou.beans.PichinchaHttpSession;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cache.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ronaldo on 24/02/2017.
 */
@Component
public class RecipientListRoute extends RouteBuilder {

    @Autowired
    private AggregationList aggregation;

    @Autowired
    private PichinchaHttpSession session;


    @Override
    public void configure() throws Exception {
        from("seda:recipientListSeda")
                .id("recipient list seda")
                .log(LoggingLevel.INFO, "Recipient List Seda")
                .throttle(1)
                .timePeriodMillis(10000)
                .to("direct:recipientListExample");

        from("direct:recipientListExample")
                .id("recipient list route")
                .recipientList(header("endpointList").tokenize(","))
                .parallelProcessing()
                .stopOnException()
                .aggregationStrategy(aggregation)
                .to("seda:sendToCache");

        from("seda:sendToCache")
                .setHeader(CacheConstants.CACHE_OPERATION, constant(CacheConstants.CACHE_OPERATION_URL_ADD))
                .setHeader(CacheConstants.CACHE_KEY, constant("Ralph_Waldo_Emerson"))
                .to("cache://movieFindCache");
    }
}
