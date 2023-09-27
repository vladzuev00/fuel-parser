package by.aurorasoft.fuelsearcher.service.searchersparser.handler.context;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearchingManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext.createCollectedMetadataContext;
import static by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext.createNotCollectedMetadataContext;

//TODO: refactor tests miwth mocked methods 'createCollectedMetadataContext' and 'createNotCollectedMetadataContext'
@Component
public final class SearchersParsingContextFactory {
    private final PropertyMetadataSearchingManager propertyMetadataSearchingManager;
    private final boolean metadataCollectingRequired;

    public SearchersParsingContextFactory(final PropertyMetadataSearchingManager propertyMetadataSearchingManager,
                                          @Value("${metadata-refreshing.enable}") final boolean metadataRefreshingEnabled) {
        this.propertyMetadataSearchingManager = propertyMetadataSearchingManager;
        this.metadataCollectingRequired = metadataRefreshingEnabled;
    }

    public SearchersParsingContext create() {
        return this.metadataCollectingRequired
                ? createCollectedMetadataContext(this.propertyMetadataSearchingManager)
                : createNotCollectedMetadataContext();
    }
}
