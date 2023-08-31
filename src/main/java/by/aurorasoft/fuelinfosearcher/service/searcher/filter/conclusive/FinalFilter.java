package by.aurorasoft.fuelinfosearcher.service.searcher.filter.conclusive;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findFirstRowByContent;

public abstract class FinalFilter extends Filter<Optional<XWPFTableRow>> {

    public FinalFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
    }

    @Override
    protected final Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                                  final String filtrationValue,
                                                  final int filtrationCellIndex) {
        return findFirstRowByContent(
                rows,
                filtrationCellIndex,
                filtrationValue
        );
    }
}
