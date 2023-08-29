package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.filter.exception;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

public final class NoSuchFilterException extends NoSuchKeyException {

    public NoSuchFilterException() {

    }

    public NoSuchFilterException(final String key) {
        super(key);
    }

    public NoSuchFilterException(final Exception cause) {
        super(cause);
    }

    public NoSuchFilterException(final String key, final Exception cause) {
        super(key, cause);
    }
}
