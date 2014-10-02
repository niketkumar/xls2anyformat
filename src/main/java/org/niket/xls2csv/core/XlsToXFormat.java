package org.niket.xls2csv.core;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.niket.xls2csv.sandbox.SandBoxedTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by niket on 2/10/14.
 */
public class XlsToXFormat<X extends XFormat, S extends OutputStream> implements SandBoxedTask<OutputStreams<S>> {
    private static final Logger Log = LoggerFactory.getLogger(XlsToXFormat.class);

    private final InputStream sourceInputStream;
    private final OutputStreams<S> outputStreams;
    private final CoreConfig coreConfig;
    private final XlsRowToXFormat<X> converter;

    public XlsToXFormat(InputStream sourceInputStream, OutputStreams<S> outputStreams,
                        CoreConfig coreConfig, XlsRowToXFormat<X> converter) {
        this.sourceInputStream = sourceInputStream;
        this.outputStreams = outputStreams;
        this.coreConfig = coreConfig;
        this.converter = converter;
    }

    @Override
    public OutputStreams<S> execute() throws Exception {
        Workbook workbook = WorkbookFactory.create(sourceInputStream);

        int numberOfSheets = workbook.getNumberOfSheets();
        validateNumberOfSheets(numberOfSheets);

        for (int i = 0; i < numberOfSheets; i++) {
            if (Thread.interrupted()) throw new Exception("Interrupted, stopping...");
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            Log.info("Parsing Sheet: {}", sheetName);
            String uniqueSheetName = sheetName + System.currentTimeMillis();
            Log.info("Sheet Unique Name: {}", uniqueSheetName);
            final S out = outputStreams.addOutputStream(uniqueSheetName);
            sheet.forEach(row -> {
                try {
                    if (Thread.interrupted()) throw new Exception("Interrupted, stopping...");
                    Log.debug("Parsing Sheet: {}, RowNum: {}", sheetName, row.getRowNum());
                    out.write(converter.apply(row).toBytes());
                } catch (Exception e) {
                    Log.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            });
        }
        return outputStreams;
    }

    private void validateNumberOfSheets(int numberOfSheets) {
        if (numberOfSheets == 0)
            throw new RuntimeException("No Sheet found in the given Workbook");
        if (numberOfSheets > coreConfig.getMaxSheetsAllowed())
            throw new RuntimeException("Too many Sheets found in the given Workbook. Allowed: "
                    + coreConfig.getMaxSheetsAllowed() + ", Found: " + numberOfSheets);
    }
}
