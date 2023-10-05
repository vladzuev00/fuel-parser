package com.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph;

import org.junit.Test;

import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findStaticFieldValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class QuoteParagraphCorrectorTest {
    private static final String FIELD_NAME_REPLACED_REGEX = "REPLACED_REGEX";

    private final QuoteParagraphCorrector corrector = new QuoteParagraphCorrector();

    @Test
    public void replacementShouldBeCreated() {
        final String actual = this.corrector.createReplacement(null);
        final String expected = "\"";
        assertEquals(expected, actual);
    }

    @Test
    public void contentsShouldMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final Stream<String> givenContents = Stream.of("«", "»");

        final boolean actual = givenContents.allMatch(content -> content.matches(givenReplacedRegex));
        assertTrue(actual);
    }

    @Test
    public void contentShouldNotMatchReplacedRegex() {
        final String givenReplacedRegex = findReplacedRegex();
        final Stream<String> givenContents = Stream.of("<<", ">>", "");

        final boolean actual = givenContents.noneMatch(content -> content.matches(givenReplacedRegex));
        assertTrue(actual);
    }

    private static String findReplacedRegex() {
        return findStaticFieldValue(
                QuoteParagraphCorrector.class,
                FIELD_NAME_REPLACED_REGEX,
                String.class
        );
    }
}
