package com.aurorasoft.fuelsearcher.it.refreshedmetadataloading.argumentsprovider.table;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;

import java.util.Set;

public final class ExpectedFourteenthTableMetadataProvider extends ExpectedTableMetadataProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";

    public ExpectedFourteenthTableMetadataProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Set<PropertyMetadata> providePropertiesMetadata() {
        return Set.of(
                PropertyMetadata.builder()
                        .propertyName("норма внесения")
                        .allowableValues(new String[]{"Менее 30", "30-50", "Более 50"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("трактор")
                        .allowableValues(new String[]{"FENDT 936", "CASE IN MAGNUM 340", "JOHN DEERE 6930"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("механизм")
                        .allowableValues(new String[]{"МЖТ-20", "МЖТ-16"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("группа дорог")
                        .allowableValues(new String[]{"Первая группа дорог", "Вторая группа дорог", "Третья группа дорог"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("расстояние транспортировки")
                        .allowableValues(new String[]{"0.25...0.75", "0.76...1.25", "1.26...1.75", "1.76...2.25", "2.26...2.75", "2.76...3.25", "3.26...4", "4.1...5", "5.1...6", "6.1...7", "7.1...8", "8.1...9", "9.1...10", "10.1...12", "12.1...14", "14.1...16", "16.1...18", "18.1...21", "21.1...24", "24.1...28", "28.1...32"})
                        .build()
        );
    }
}
