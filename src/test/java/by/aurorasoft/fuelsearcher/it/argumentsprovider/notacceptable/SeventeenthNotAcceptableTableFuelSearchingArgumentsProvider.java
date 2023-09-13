package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SeventeenthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВОРОШЕНИЕ СЕНА";

    public SeventeenthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("ГРЛ-9.6")
                                .routingLength("Менее 150")
                                .build())
                        .failedPropertyNames(new String[]{"ширина захвата"})
                        .build()
        );
    }
}
