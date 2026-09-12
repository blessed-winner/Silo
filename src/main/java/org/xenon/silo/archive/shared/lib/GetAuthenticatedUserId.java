package org.xenon.silo.archive.shared.lib;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetAuthenticatedUserId {
    public UUID getAuthenticatedUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            throw new IllegalStateException("No authenticated user");
        }

        return (UUID) authentication.getPrincipal();
    }
}
