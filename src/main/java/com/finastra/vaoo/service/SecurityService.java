package com.finastra.vaoo.service;

public final class SecurityService {

    private SecurityService() {
    }

    public static boolean validate(String token) {
        return "AD8F276E479AE52E7BF5DF8C22363".equals(token);
    }
}
