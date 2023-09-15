package by.aurorasoft.fuelsearcher.service.searcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.header.FuelHeaderMetadata;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher.SearcherBuilder;
import lombok.Builder;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelSearcherTest {
    private static final String FIELD_NAME_FUEL_TABLE = "table";
    private static final String FIELD_NAME_HEADER_METADATA = "headerMetadata";
    private static final String FIELD_NAME_FILTER_CHAIN_BUILDER = "filterChainBuilder";

    @Test
    public void aliasShouldBeFound() {
        final String givenTableName = "table-name";
        final FuelTable givenTable = createTable(givenTableName);
        final FuelSearcher givenSearcher = createSearcher(givenTable);

        final String actual = givenSearcher.findAlias();
        assertEquals(givenTableName, actual);
    }

    @Test
    public void fuelTableShouldBeAccumulatedByBuilder()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FuelTable givenTable = mock(FuelTable.class);

        givenBuilder.table(givenTable);

        final FuelTable actual = findFuelTable(givenBuilder);
        assertSame(givenTable, actual);
    }

    @Test
    public void headerMetadataShouldBeAccumulated()
            throws Exception {
        final TestSearcherBuilder givenBuilder = TestSearcherBuilder.builder().build();
        final FuelHeaderMetadata givenMetadata = mock(FuelHeaderMetadata.class);

        givenBuilder.headerMetadata(givenMetadata);

        final FuelHeaderMetadata actual = findHeaderMetadata(givenBuilder);
        assertSame(givenMetadata, actual);
    }

    private static FuelTable createTable(final String name) {
        final FuelTable givenTable = mock(FuelTable.class);
        when(givenTable.getName()).thenReturn(name);
        return givenTable;
    }

    private static FuelSearcher createSearcher(final FuelTable table) {
        return new TestFuelSearcher(table, null, null, null);
    }

    private static FuelSearcher createSearcher(final XWPFTable subTable) {
        return new TestFuelSearcher(null, null, null, null, subTable);
    }

    private static FuelTable findFuelTable(final SearcherBuilder<?> builder)
            throws Exception {
        return findProperty(builder, FIELD_NAME_FUEL_TABLE, FuelTable.class);
    }

    private static FuelHeaderMetadata findHeaderMetadata(final SearcherBuilder<?> builder)
            throws Exception {
        return findProperty(builder, FIELD_NAME_HEADER_METADATA, FuelHeaderMetadata.class);
    }

    private static <P> P findProperty(final SearcherBuilder<?> builder,
                                      final String fieldName,
                                      final Class<P> propertyType)
            throws Exception {
        return findProperty(
                builder,
                fieldName,
                SearcherBuilder.class,
                propertyType
        );
    }

    private static <S, P> P findProperty(final S source,
                                         final String fieldName,
                                         final Class<S> sourceType,
                                         final Class<P> propertyType)
            throws Exception {
        final Field field = sourceType.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(source);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }

    private static final class TestFuelSearcher extends FuelSearcher {
        private final XWPFTable subTable;

        public TestFuelSearcher(final FuelTable table,
                                final Map<String, Integer> fuelOffsetsByHeaders,
                                final FilterChain filterChain,
                                final SpecificationPropertyExtractor headerExtractor) {
            this(table, fuelOffsetsByHeaders, filterChain, headerExtractor, null);
        }

        public TestFuelSearcher(final FuelTable table,
                                final Map<String, Integer> fuelOffsetsByHeaders,
                                final FilterChain filterChain,
                                final SpecificationPropertyExtractor headerExtractor,
                                final XWPFTable subTable) {
            super(table, fuelOffsetsByHeaders, filterChain, headerExtractor);
            this.subTable = subTable;
        }

        @Override
        protected Optional<XWPFTable> findSubTable(final List<IBodyElement> elements,
                                                   final FuelSpecification specification) {
            return ofNullable(this.subTable);
        }
    }

    private static final class TestSearcherBuilder extends SearcherBuilder<TestFuelSearcher> {
        private final boolean validElements;
        private final String notValidElementsMessage;
        private final Object firstAdditionalProperty;
        private final Object secondAdditionalProperty;

        @Builder
        public TestSearcherBuilder(final boolean validElements,
                                   final String notValidElementsMessage,
                                   final Object firstAdditionalProperty,
                                   final Object secondAdditionalProperty) {
            this.validElements = validElements;
            this.notValidElementsMessage = notValidElementsMessage;
            this.firstAdditionalProperty = firstAdditionalProperty;
            this.secondAdditionalProperty = secondAdditionalProperty;
        }


        @Override
        protected boolean isValidElements(final List<IBodyElement> elements) {
            return this.validElements;
        }

        @Override
        protected String findNotValidElementsMessage() {
            return this.notValidElementsMessage;
        }

        @Override
        protected TestFuelSearcher build(final FuelTable fuelTable,
                                         final Map<String, Integer> fuelOffsetsByHeaders,
                                         final FilterChain filterChain,
                                         final SpecificationPropertyExtractor headerExtractor) {
            return new TestFuelSearcher(
                    fuelTable,
                    fuelOffsetsByHeaders,
                    filterChain,
                    headerExtractor
            );
        }

        @Override
        protected Stream<Object> findAdditionalProperties() {
            return Stream.of(this.firstAdditionalProperty, this.secondAdditionalProperty);
        }
    }
}
