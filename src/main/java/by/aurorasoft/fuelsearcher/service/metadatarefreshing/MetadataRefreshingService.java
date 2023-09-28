package by.aurorasoft.fuelsearcher.service.metadatarefreshing;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.service.PropertyMetadataService;
import by.aurorasoft.fuelsearcher.crud.service.TableMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MetadataRefreshingService {
    private final TableMetadataService tableMetadataService;
    private final PropertyMetadataService propertyMetadataService;
    private final SearchersParsingResult parsingResult;

    @EventListener(ApplicationStartedEvent.class)
    public void refresh() {
        this.tableMetadataService.deleteAll();
        this.saveNewMetadata();
    }

    private void saveNewMetadata() {
        final List<TableMetadata> newTablesMetadata = this.findNewTablesMetadata();
        final List<TableMetadata> savedTablesMetadata = this.tableMetadataService.saveAll(newTablesMetadata);
        final List<PropertyMetadata> newPropertiesMetadata = findPropertiesMetadata(savedTablesMetadata);
        this.propertyMetadataService.saveAll(newPropertiesMetadata);
    }

    private List<TableMetadata> findNewTablesMetadata() {
        final Optional<List<TableMetadata>> optionalNewTablesMetadata = this.parsingResult.takeAwayTablesMetadata();
        return optionalNewTablesMetadata
                .filter(newTablesMetadata -> !newTablesMetadata.isEmpty())
                .orElseThrow(NoNewTablesMetadataException::new);
    }

    private static List<PropertyMetadata> findPropertiesMetadata(final List<TableMetadata> tablesMetadata) {
        return tablesMetadata.stream()
                .flatMap(MetadataRefreshingService::findPropertiesMetadata)
                .toList();
    }

    private static Stream<PropertyMetadata> findPropertiesMetadata(final TableMetadata tableMetadata) {
        return tableMetadata.getPropertiesMetadata()
                .stream()
                .map(propertyMetadata -> replaceTableMetadata(propertyMetadata, tableMetadata));
    }

    private static PropertyMetadata replaceTableMetadata(final PropertyMetadata source,
                                                         final TableMetadata tableMetadata) {
        return new PropertyMetadata(
                source.getId(),
                source.getPropertyName(),
                source.getAllowableValues(),
                tableMetadata.getId()
        );
    }

    private static final class NoNewTablesMetadataException extends RuntimeException {

        public NoNewTablesMetadataException() {

        }

        @SuppressWarnings("unused")
        public NoNewTablesMetadataException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NoNewTablesMetadataException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoNewTablesMetadataException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
