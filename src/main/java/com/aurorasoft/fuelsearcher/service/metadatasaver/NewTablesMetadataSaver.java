package com.aurorasoft.fuelsearcher.service.metadatasaver;

import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Component
public final class NewTablesMetadataSaver {
    private final String filePath;

    public NewTablesMetadataSaver(@Value("${metadata.file-path}") final String filePath) {
        this.filePath = filePath;
    }

    public void save(final List<TableMetadata> newTablesMetadata) {
        try (final ObjectOutputStream outputStream = this.createObjectOutputStream()) {
            newTablesMetadata.forEach(newTableMetadata -> save(newTableMetadata, outputStream));
        } catch (final IOException cause) {
            throw new NewTablesMetadataSavingException(cause);
        }
    }

    private ObjectOutputStream createObjectOutputStream()
            throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(this.filePath);
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        return new ObjectOutputStream(bufferedOutputStream);
    }

    private static void save(final TableMetadata metadata, final ObjectOutputStream outputStream) {
        try {
            outputStream.writeObject(metadata);
        } catch (final IOException cause) {
            throw new NewTablesMetadataSavingException(cause);
        }
    }

    private static final class NewTablesMetadataSavingException extends RuntimeException {

        @SuppressWarnings("unused")
        public NewTablesMetadataSavingException() {

        }

        @SuppressWarnings("unused")
        public NewTablesMetadataSavingException(final String description) {
            super(description);
        }

        public NewTablesMetadataSavingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NewTablesMetadataSavingException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
