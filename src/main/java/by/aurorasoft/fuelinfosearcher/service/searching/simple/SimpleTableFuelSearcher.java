package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import lombok.Builder;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class SimpleTableFuelSearcher extends AbstractTableFuelSearcher {

    @Builder
    public SimpleTableFuelSearcher(final FuelTable fuelTable,
                                   final String[] fuelHeaders,
                                   final RowFilterChain filterChain,
                                   final Function<FuelSpecification, String> fuelHeaderCellValueExtractor) {
        super(fuelTable, fuelHeaders, filterChain, fuelHeaderCellValueExtractor);
    }

    @Override
    protected Optional<XWPFTable> findElementTable(final FuelTable fuelTable, final FuelSpecification specification) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable elementTable = (XWPFTable) firstElement;
        return Optional.of(elementTable);
    }
}
