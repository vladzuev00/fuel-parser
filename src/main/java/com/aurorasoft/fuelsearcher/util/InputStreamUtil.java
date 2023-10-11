package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.util.Optional;

import static java.util.Optional.empty;

@UtilityClass
public final class InputStreamUtil {

    public static ObjectInputStream createObjectInputStream(final String filePath) {
        try {
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            return new ObjectInputStream(bufferedInputStream);
        } catch (final IOException cause) {
            throw new InputStreamException(cause);
        }
    }

    public static void closeStream(final InputStream inputStream) {
        try {
            inputStream.close();
        } catch (final IOException cause) {
            throw new InputStreamException(cause);
        }
    }

    public static <T> Optional<T> readObjectIfExist(final ObjectInputStream inputStream, final Class<T> objectType) {
        try {
            final Object object = inputStream.readObject();
            final T concreteObject = objectType.cast(object);
            return Optional.of(concreteObject);
        } catch (final EOFException endDeserializationException) {
            return empty();
        } catch (final IOException | ClassNotFoundException cause) {
            throw new RuntimeException(cause);
        }
    }

    private static final class InputStreamException extends RuntimeException {

        public InputStreamException() {

        }

        public InputStreamException(final String description) {
            super(description);
        }

        public InputStreamException(final Exception cause) {
            super(cause);
        }

        public InputStreamException(final String description, final Exception cause) {
            super(description, cause);
        }
    }

}
