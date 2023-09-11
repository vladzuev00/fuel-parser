package by.aurorasoft.fuelsearcher.model.filter.factory.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.RoadGroupGroupFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.RoadGroupExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public final class RoadGroupGroupFilterFactoryTest {
    private final RoadGroupGroupFilterFactory factory = new RoadGroupGroupFilterFactory(null);

    @Test
    public void filterShouldBeCreated() {
        final RoadGroupExtractor givenRoadGroupExtractor = mock(RoadGroupExtractor.class);
        final int givenFiltrationCellIndex = 1;

        final RoadGroupGroupFilter actual = this.factory.create(givenRoadGroupExtractor, givenFiltrationCellIndex);

        assertSame(givenRoadGroupExtractor, actual.getFiltrationValueExtractor());
        assertEquals(givenFiltrationCellIndex, actual.getFiltrationCellIndex());
    }
}
