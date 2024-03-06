package com.kosign.vcrprojectsecurity.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {
    private static Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static String getUsername(){return getAuth().getName();}
}
