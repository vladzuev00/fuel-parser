package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group;

import by.aurorasoft.fuelinfosearcher.model.Specification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSowingNorm;

public final class SowingNormRowFilter extends GroupFilter {
    private static final String GROUP_VALUE_REGEX = "Норма высева (семян )?\\d+(-\\d+)? кг/га";

    @Override
    protected String extractFilteringValue(final Specification specification) {
        return extractSowingNorm(specification);
    }

    @Override
    protected String findGroupValueRegex() {
        return GROUP_VALUE_REGEX;
    }

}
