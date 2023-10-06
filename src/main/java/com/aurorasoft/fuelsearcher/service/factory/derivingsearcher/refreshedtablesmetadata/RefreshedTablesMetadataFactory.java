package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata;

import com.aurorasoft.fuelsearcher.model.FuelTable;
import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.DerivingSearcherFactory;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.PropertyMetadataSearchingManager;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class RefreshedTablesMetadataFactory extends DerivingSearcherFactory<TableMetadata> {
    private final PropertyMetadataSearchingManager propertyMetadataSearchingManager;

    public RefreshedTablesMetadataFactory(final List<FuelSearcher> searchers,
                                          final PropertyMetadataSearchingManager propertyMetadataSearchingManager) {
        super(searchers);
        this.propertyMetadataSearchingManager = propertyMetadataSearchingManager;
    }

    @Override
    protected TableMetadata createDerivedObject(final FuelSearcher searcher) {
        final String tableName = searcher.findTableName();
        final List<PropertyMetadata> propertiesMetadata = this.findPropertiesMetadata(searcher);
        return new TableMetadata(tableName, propertiesMetadata);
    }

    private List<PropertyMetadata> findPropertiesMetadata(final FuelSearcher searcher) {
        final FuelTable table = searcher.getTable();
        return searcher.findUsedPropertyMetadataSources()
                .map(metadataSource -> this.propertyMetadataSearchingManager.find(table, metadataSource))
                .toList();
    }
}
