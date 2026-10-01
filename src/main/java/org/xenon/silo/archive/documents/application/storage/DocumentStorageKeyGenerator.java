package org.xenon.silo.archive.documents.application.storage;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DocumentStorageKeyGenerator {
    public String generate(UUID userId, String originalFileName){
        String extension = extractExtension(originalFileName);

        return "/user/%s/%s/%s".formatted(
                userId,
                UUID.randomUUID(),
                extension
        );
    }

    public String extractExtension(String fileName){
        if(fileName==null){
            return "";
        }

        if(fileName.lastIndexOf(".")==-1){
            return "";
        }

        return fileName.substring(fileName.lastIndexOf("."));
    }
}
