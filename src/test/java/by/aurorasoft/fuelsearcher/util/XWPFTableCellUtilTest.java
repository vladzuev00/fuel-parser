package by.aurorasoft.fuelsearcher.util;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.extractContent;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class XWPFTableCellUtilTest {

    @Test
    public void cellShouldBeEmpty() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            markParagraphAsEmpty(firstGivenParagraph, mockedParagraphUtil);
            markParagraphAsEmpty(secondGivenParagraph, mockedParagraphUtil);

            final boolean actual = XWPFTableCellUtil.isEmpty(givenCell);
            assertTrue(actual);
        }
    }

    @Test
    public void cellShouldNotBeEmpty() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            markParagraphAsEmpty(firstGivenParagraph, mockedParagraphUtil);
            markParagraphAsNotEmpty(secondGivenParagraph, mockedParagraphUtil);

            final boolean actual = XWPFTableCellUtil.isEmpty(givenCell);
            assertFalse(actual);
        }
    }

    @Test
    public void cellWithoutParagraphsShouldBeEmpty() {
        final XWPFTableCell givenCell = createCell();

        final boolean actual = XWPFTableCellUtil.isEmpty(givenCell);
        assertTrue(actual);
    }

    @Test
    public void cellContentShouldBeExtracted() {
        final XWPFParagraph firstGivenParagraph = mock(XWPFParagraph.class);
        final XWPFParagraph secondGivenParagraph = mock(XWPFParagraph.class);
        final XWPFTableCell givenCell = createCell(firstGivenParagraph, secondGivenParagraph);

        final String givenContentFirstParagraph = "content-1";
        final String givenContentSecondParagraph = "content-2";

        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            bindContentWithParagraph(firstGivenParagraph, givenContentFirstParagraph, mockedParagraphUtil);
            bindContentWithParagraph(secondGivenParagraph, givenContentSecondParagraph, mockedParagraphUtil);

            final String actual = extractContent(givenCell);
            final String expected = "content-1 content-2";
            assertEquals(expected, actual);
        }
    }

    @Test
    public void contentOfCellWithoutParagraphsShouldBeExtracted() {
        final XWPFTableCell givenCell = createCell();

        final String actual = extractContent(givenCell);
        final String expected = "";
        assertEquals(expected, actual);
    }

    private static XWPFTableCell createCell(final XWPFParagraph... paragraphs) {
        final List<XWPFParagraph> paragraphsAsList = paragraphs != null ? asList(paragraphs) : emptyList();
        final XWPFTableCell cell = mock(XWPFTableCell.class);
        when(cell.getParagraphs()).thenReturn(paragraphsAsList);
        return cell;
    }

    private static void markParagraphAsEmpty(final XWPFParagraph paragraph,
                                             final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        markParagraphByEmpty(paragraph, mockedParagraphUtil, true);
    }

    private static void markParagraphAsNotEmpty(final XWPFParagraph paragraph,
                                                final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        markParagraphByEmpty(paragraph, mockedParagraphUtil, false);
    }

    private static void markParagraphByEmpty(final XWPFParagraph paragraph,
                                             final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil,
                                             final boolean empty) {
        mockedParagraphUtil.when(() -> XWPFParagraphUtil.isEmpty(same(paragraph))).thenReturn(empty);
    }

    private static void bindContentWithParagraph(final XWPFParagraph paragraph,
                                                 final String content,
                                                 final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        mockedParagraphUtil.when(() -> XWPFParagraphUtil.extractText(same(paragraph))).thenReturn(content);
    }
}
