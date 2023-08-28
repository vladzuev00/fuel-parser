package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractFertilizerType;

public final class FertilizerTypeRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "(Гранулированные удобрений)|(Кристаллические удобрения)|(Пылевидные удобрения)";

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractFertilizerType(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }
}
