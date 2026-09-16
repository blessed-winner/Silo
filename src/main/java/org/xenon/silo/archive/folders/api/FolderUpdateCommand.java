package org.xenon.silo.archive.folders.api;


import java.util.UUID;

public record FolderUpdateCommand(
           String name,
           UUID parentId
   ){}
