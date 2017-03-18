package com.tiou.beans;

import org.restlet.Request;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronaldo on 16/03/2017.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PichinchaHttpSession {

    private List<String> errorMessages = new ArrayList<String>();

    public PichinchaHttpSession() {
        System.out.println("New Instance");
    }

    public List<String> validate(Request request) {
        errorMessages.add("Validation Failed");
        return errorMessages;
    }
}
