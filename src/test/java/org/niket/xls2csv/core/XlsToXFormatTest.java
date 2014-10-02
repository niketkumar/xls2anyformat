package org.niket.xls2csv.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.niket.xls2csv.core.csv.CsvFormat;
import org.niket.xls2csv.core.csv.XlsRowToCsv;
import org.niket.xls2csv.sandbox.SandBoxInjector;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static java.lang.Thread.currentThread;
import static org.junit.Assert.assertEquals;

public class XlsToXFormatTest {
    private Injector injector;

    @Before
    public void setUp() throws Exception {
        injector = Guice.createInjector(new SandBoxInjector(), new CoreInjector());
    }

    @After
    public void tearDown() throws Exception {
        injector = null;
    }

    @Test
    public void testXls() throws Exception {
        try (InputStream inputStream = currentThread().getContextClassLoader()
                .getResourceAsStream("test1.xls")) {
            try (StringOutputStreams outputStreams = new StringOutputStreams("niket", "problem1")) {
                XlsToXFormat<CsvFormat, ByteArrayOutputStream> converter = new XlsToXFormat<>(
                        inputStream, outputStreams,
                        injector.getInstance(CoreConfig.class),
                        injector.getInstance(XlsRowToCsv.class));
                OutputStreams<ByteArrayOutputStream> results = converter.execute();
                results.close();
                results.forEach(entry -> {
                    String streamId = entry.getKey();
                    String result = entry.getValue().toString();
                    assertEquals(true, streamId.startsWith("Sheet1"));
                    assertEquals("ImageId,Label\n" +
                            "1,22.2\n" +
                            "2,2014.01.22 00:00:00 IST\n" +
                            "3,9\n" +
                            "4,9\n" +
                            "5\n", result);
                });
                assertEquals("niket", results.getUserId());
                assertEquals("problem1", results.getProblemId());
            }
        }
    }

    @Test
    public void testXlsX() throws Exception {
        try (InputStream inputStream = currentThread().getContextClassLoader()
                .getResourceAsStream("test2.xlsx")) {
            try (StringOutputStreams outputStreams = new StringOutputStreams("niket", "problem1")) {
                XlsToXFormat<CsvFormat, ByteArrayOutputStream> converter = new XlsToXFormat<>(
                        inputStream, outputStreams,
                        injector.getInstance(CoreConfig.class),
                        injector.getInstance(XlsRowToCsv.class));
                OutputStreams<ByteArrayOutputStream> results = converter.execute();
                results.close();
                results.forEach(entry -> {
                    String streamId = entry.getKey();
                    String result = entry.getValue().toString();
                    assertEquals(true, streamId.startsWith("Sheet1"));
                    assertEquals("ImageId,Label\n" +
                            "1,22.2\n" +
                            "2,2014.01.22 00:00:00 IST\n" +
                            "3,9\n" +
                            "4,9\n" +
                            "5\n", result);
                });
                assertEquals("niket", results.getUserId());
                assertEquals("problem1", results.getProblemId());
            }
        }
    }

    @Test(expected = RuntimeException.class)
    public void testManySheets() throws Exception {
        try (InputStream inputStream = currentThread().getContextClassLoader()
                .getResourceAsStream("many_sheets.xlsx")) {
            try (StringOutputStreams outputStreams = new StringOutputStreams("niket", "problem1")) {
                XlsToXFormat<CsvFormat, ByteArrayOutputStream> converter = new XlsToXFormat<>(
                        inputStream, outputStreams,
                        injector.getInstance(CoreConfig.class),
                        injector.getInstance(XlsRowToCsv.class));
                OutputStreams<ByteArrayOutputStream> results = converter.execute();
            }
        }
    }
}