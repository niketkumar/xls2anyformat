package org.niket.xls2csv.core.csv;

import org.apache.poi.ss.usermodel.Row;
import org.niket.xls2csv.core.CellToStringExtractor;
import org.niket.xls2csv.core.XlsRowToXFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Given a Row, returns row data in CSV format.
 * Created by niket on 2/10/14.
 */
@Singleton
public class XlsRowToCsv implements XlsRowToXFormat<CsvFormat> {
    private static final Logger Log = LoggerFactory.getLogger(XlsRowToCsv.class);
    static String NEW_LINE = System.getProperty("line.separator");

    private final CellToStringExtractor cellToString;

    @Inject
    public XlsRowToCsv(CellToStringExtractor cellToString) {
        this.cellToString = cellToString;
    }

    @Override
    public CsvFormat apply(Row row) {
        Log.debug("Converting Row {} to CSV", row);
        return new CsvFormat(
                StreamSupport.stream(row.spliterator(), false)
                        .map(cellToString::apply)
                        .collect(Collectors.joining(",")) + NEW_LINE);
    }
}
