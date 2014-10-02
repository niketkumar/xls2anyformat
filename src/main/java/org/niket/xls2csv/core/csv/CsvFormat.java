package org.niket.xls2csv.core.csv;

import org.niket.xls2csv.core.XFormat;

/**
 * Created by niket on 2/10/14.
 */
public class CsvFormat implements XFormat {
    private final String row;

    public CsvFormat(String row) {
        this.row = row;
    }

    @Override
    public byte[] toBytes() {
        return row.getBytes();
    }
}
