package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class ThirdTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3522")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("20–22")
                                .routingLength("Менее 150")
                                .soilType("Минеральные почвы")
                                .build(),
                        optionalFuelInfoFactory.apply(8.5, 23.7)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        optionalFuelInfoFactory.apply(11.4, 20.5)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("not exist")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing plough mark
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("not exist")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing corpus count
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("-1")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing ploughing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("1500-2000")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("5000-5001")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing soil type
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25–27")
                                .routingLength("Более 1000")
                                .soilType("Несуществующие почвы")
                                .build(),
                        empty()
                )
        );
    }

}
