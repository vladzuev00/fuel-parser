package by.aurorasoft.fuelsearcher.model.header;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpreadRateExtractor;

import java.util.Map;

public final class SpreadRateHeaderMetadata extends FuelHeaderMetadata {

    public SpreadRateHeaderMetadata(final SpreadRateExtractor spreadRateExtractor,
                                    final Map<String, Integer> fuelOffsetsByValues) {
        super(spreadRateExtractor, fuelOffsetsByValues);
    }

}
