package org.xenon.silo.archive.folders.api;

  public record FolderResponse(
        String id,
        String name,
        String parentName
  ){}
