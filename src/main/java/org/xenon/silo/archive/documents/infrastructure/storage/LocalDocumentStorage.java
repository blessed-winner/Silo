package org.xenon.silo.archive.documents.infrastructure.storage;

import org.springframework.stereotype.Component;
import org.xenon.silo.archive.documents.domain.DocumentStorage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class LocalDocumentStorage implements DocumentStorage {
    private final Path rootLocation;

    public LocalDocumentStorage(StorageProperties storageProperties){
        this.rootLocation = Paths.get(storageProperties.getLocation())
                                 .toAbsolutePath()
                                 .normalize();
    }

    @Override
    public void store(String storageKey, InputStream inputStream){
        try{
            Path target = rootLocation.resolve(storageKey).normalize();

            Files.createDirectories(target.getParent());

            Files.copy(inputStream,
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

        }catch(IOException e){
            //TODO:Create a custom exception to be thrown
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public InputStream retrieve(String storageKey){
        try{
            Path target = rootLocation.resolve(storageKey).normalize();

            return Files.newInputStream(target);
        }catch(IOException e){
            //TODO:Create a custom exception to be thrown
            throw new RuntimeException("Failed to retrieve file",e);
        }
    }

    @Override
    public void delete(String storageKey){
        try{
            Path target = rootLocation.resolve(storageKey).normalize();

            Files.deleteIfExists(target);
        } catch (IOException e){
            //TODO:Create a custom exception to be thrown
            throw new RuntimeException("Failed to delete file", e);
        }
    }
}
