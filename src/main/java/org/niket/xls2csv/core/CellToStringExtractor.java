package org.niket.xls2csv.core;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;

/**
 * Created by niket on 2/10/14.
 */
@Singleton
public class CellToStringExtractor implements CellValueExtractor<String> {
    private static final Logger Log = LoggerFactory.getLogger(CellToStringExtractor.class);

    private final CoreConfig coreConfig;

    @Inject
    public CellToStringExtractor(CoreConfig coreConfig) {
        this.coreConfig = coreConfig;
    }

    @Override
    public String apply(Cell cell) {
        Log.debug("Extracting Cell {} As String", cell);
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat(coreConfig.getDateFormat()).format(cell.getDateCellValue());
                } else {
                    double number = cell.getNumericCellValue();
                    if (number % 1 == 0) {
                        return String.valueOf((long) number);
                    } else {
                        return String.valueOf(number);
                    }
                }
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}
