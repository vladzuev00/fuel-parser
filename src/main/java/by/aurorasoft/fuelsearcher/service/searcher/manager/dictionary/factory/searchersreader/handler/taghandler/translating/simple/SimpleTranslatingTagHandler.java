package by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.simple;

import by.aurorasoft.fuelsearcher.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.dictionary.Dictionary;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.TranslatingTagHandler;
import by.aurorasoft.fuelsearcher.service.searcher.manager.dictionary.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

import java.util.stream.Stream;

public abstract class SimpleTranslatingTagHandler<V extends Translatable> extends TranslatingTagHandler<V> {

    public SimpleTranslatingTagHandler(final String tagName,
                                       final Dictionary<V> dictionary,
                                       final NoSuchKeyException.NoSuchKeyExceptionFactory<?> noSuchKeyExceptionFactory) {
        super(tagName, dictionary, noSuchKeyExceptionFactory);
    }

    @Override
    protected final Stream<String> findKeys(final SearchersParsingContext context) {
        final String key = context.getLastContent();
        return Stream.of(key);
    }
}
