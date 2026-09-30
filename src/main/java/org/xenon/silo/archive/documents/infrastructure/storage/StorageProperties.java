package org.xenon.silo.archive.documents.infrastructure.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String location;

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }
}
