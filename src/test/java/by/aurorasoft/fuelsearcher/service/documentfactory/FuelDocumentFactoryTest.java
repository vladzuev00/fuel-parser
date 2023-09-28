package by.aurorasoft.fuelsearcher.service.documentfactory;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.factory.documentfactory.FuelDocumentFactory;
import by.aurorasoft.fuelsearcher.service.factory.documentfactory.corrector.FuelDocumentCorrector;
import by.aurorasoft.fuelsearcher.service.factory.documentfactory.loader.FuelDocumentLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class FuelDocumentFactoryTest {

    @Mock
    private FuelDocumentLoader mockedLoader;

    @Mock
    private FuelDocumentCorrector mockedCorrector;

    private FuelDocumentFactory documentFactory;

    @Before
    public void initializeDocumentFactory() {
        this.documentFactory = new FuelDocumentFactory(this.mockedLoader, this.mockedCorrector);
    }

    @Test
    public void documentShouldBeCreated() {
        final String givenFilePath = "file-path";

        final FuelDocument givenDocument = mock(FuelDocument.class);
        when(this.mockedLoader.load(same(givenFilePath))).thenReturn(givenDocument);

        final FuelDocument actual = this.documentFactory.create(givenFilePath);
        assertSame(givenDocument, actual);

        verify(this.mockedCorrector, times(1)).correct(same(givenDocument));
    }

}
