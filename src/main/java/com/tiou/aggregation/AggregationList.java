package com.tiou.aggregation;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by ronaldo on 24/02/2017.
 */
@Component
public class AggregationList implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String uri = newExchange.getProperty(Exchange.RECIPIENT_LIST_ENDPOINT, String.class);
        HashMap<String, String> map;

        if (oldExchange == null) {
            map =  new HashMap<>();
            map.put(uri, newExchange.getIn().getBody(String.class));
            newExchange.getIn().setBody(map);
            return newExchange;
        }

        map = oldExchange.getIn().getBody(HashMap.class);
        map.put(uri, oldExchange.getIn().getBody(String.class));

        return oldExchange;
    }
}
