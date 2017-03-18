package com.tiou.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.stereotype.Component;

/**
 * Created by ronaldo on 28/02/2017.
 */
@Component
public class CacheConfigurationRoute extends RouteBuilder {

    @Autowired
    private CacheAutoConfiguration teste;

    @Override
    public void configure() throws Exception {
        from("cache://movieFindCache" )
                .to("log:foo");
    }
}
