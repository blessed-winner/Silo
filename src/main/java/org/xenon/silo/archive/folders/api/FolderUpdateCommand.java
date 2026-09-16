package org.xenon.silo.archive.folders.api;


import lombok.Getter;

@Getter
   public record FolderUpdateCommand(
           String name,
           String parentId
   ){}
