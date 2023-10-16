package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider;

import com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table.*;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class ExpectedDocumentMetadataProvider {
    private final List<ExpectedTableMetadataProvider> tableMetadataProviders = List.of(
            new ExpectedFirstTableMetadataProvider(),
            new SecondTablePropertyMetadataViewsProvider(),
            new ThirdTablePropertyMetadataViewsProvider(),
            new FourthTablePropertyMetadataViewsProvider(),
            new ExpectedFifthTableMetadataProvider(),
            new SixthTablePropertyMetadataViewsProvider(),
            new SeventhTablePropertyMetadataViewsProvider(),
            new ExpectedEighthTableMetadataProvider(),
            new NinthTablePropertyMetadataViewsProvider(),
            new TenthTablePropertyMetadataViewsProvider(),
            new ExpectedEleventhTableMetadataProvider(),
            new TwelfthTablePropertyMetadataViewsProvider(),
            new ThirteenthTablePropertyMetadataViewsProvider(),
            new ExpectedFourteenthTableMetadataProvider(),
            new ExpectedFifteenthTableMetadataProvider(),
            new SixteenthTablePropertyMetadataViewsProvider(),
            new SeventeenthTablePropertyMetadataViewsProvider(),
            new ExpectedEighteenthTableMetadataProvider(),
            new NineteenthTablePropertyMetadataViewsProvider(),
            new TwentiethTablePropertyMetadataViewsProvider(),
            new TwentyFirstTablePropertyMetadataViewsProvider(),
            new TwentySecondTablePropertyMetadataViewsProvider(),
            new TwentyThirdTablePropertyMetadataViewsProvider(),
            new TwentyFourthTablePropertyMetadataViewsProvider(),
            new TwentyFifthTablePropertyMetadataViewsProvider(),
            new TwentySixthTablePropertyMetadataViewsProvider(),
            new TwentySeventhTablePropertyMetadataViewsProvider()
    );

    public Set<TableMetadata> provide() {
        return this.tableMetadataProviders.stream()
                .map(ExpectedTableMetadataProvider::provide)
                .collect(toSet());
    }
}
