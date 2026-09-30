package org.xenon.silo.archive.documents.domain;

import java.io.InputStream;

public interface DocumentStorage {
    void store(String storageKey, InputStream inputStream);

    InputStream retrieve(String storageKey);

    void delete(String storageKey);
}
