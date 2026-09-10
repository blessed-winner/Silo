CREATE EXTENSION IF NOT EXISTS "pgcrypto";


-- ============================================================
-- USERS
-- ============================================================

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                       email VARCHAR(255) NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,

                       first_name VARCHAR(100) NOT NULL,
                       last_name VARCHAR(100) NOT NULL,

                       is_enabled BOOLEAN NOT NULL DEFAULT TRUE,

                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       CONSTRAINT uk_users_email UNIQUE (email)
);


-- ============================================================
-- FOLDERS
-- ============================================================

CREATE TABLE folders (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                         name VARCHAR(255) NOT NULL,

                         owner_id UUID NOT NULL,
                         parent_id UUID,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_folders_owner
                             FOREIGN KEY (owner_id)
                                 REFERENCES users(id),

                         CONSTRAINT fk_folders_parent
                             FOREIGN KEY (parent_id)
                                 REFERENCES folders(id)
);


-- ============================================================
-- DOCUMENTS
-- ============================================================

CREATE TABLE documents (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                           name VARCHAR(255) NOT NULL,
                           original_filename VARCHAR(255) NOT NULL,

                           storage_key VARCHAR(500) NOT NULL,
                           mime_type VARCHAR(100) NOT NULL,
                           file_size BIGINT NOT NULL,

                           owner_id UUID NOT NULL,
                           folder_id UUID,

                           description TEXT,

                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           deleted_at TIMESTAMP,

                           CONSTRAINT uk_documents_storage_key UNIQUE (storage_key),

                           CONSTRAINT fk_documents_owner
                               FOREIGN KEY (owner_id)
                                   REFERENCES users(id),

                           CONSTRAINT fk_documents_folder
                               FOREIGN KEY (folder_id)
                                   REFERENCES folders(id),

                           CONSTRAINT chk_documents_file_size
                               CHECK (file_size >= 0)
);


-- ============================================================
-- INDEXES
-- ============================================================

CREATE INDEX idx_folders_owner_id
    ON folders(owner_id);

CREATE INDEX idx_folders_parent_id
    ON folders(parent_id);

CREATE INDEX idx_documents_owner_id
    ON documents(owner_id);

CREATE INDEX idx_documents_folder_id
    ON documents(folder_id);

CREATE INDEX idx_documents_created_at
    ON documents(created_at);