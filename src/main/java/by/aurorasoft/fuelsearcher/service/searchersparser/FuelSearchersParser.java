package by.aurorasoft.fuelsearcher.service.searchersparser;

import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingHandler;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingHandlerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearchersParser {
    private final SAXParser saxParser;
    private final SearchersParsingHandlerFactory parsingHandlerFactory;

    public List<FuelSearcher> parse(final String filePath) {
        try {
            final SearchersParsingHandler parsingHandler = this.parsingHandlerFactory.create();
            this.saxParser.parse(filePath, parsingHandler);
            return parsingHandler.findParsedSearchers();
        } catch (final SAXException | IOException cause) {
            throw new FuelSearchersParsingException(cause);
        }
    }

    private static final class FuelSearchersParsingException extends RuntimeException {

        @SuppressWarnings("unused")
        public FuelSearchersParsingException() {

        }

        @SuppressWarnings("unused")
        public FuelSearchersParsingException(final String description) {
            super(description);
        }

        public FuelSearchersParsingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public FuelSearchersParsingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
