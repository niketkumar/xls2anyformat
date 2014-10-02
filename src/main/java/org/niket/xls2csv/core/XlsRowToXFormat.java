package org.niket.xls2csv.core;

import org.apache.poi.ss.usermodel.Row;

import java.util.function.Function;

/**
 * Converts a given Row to target format.
 * Created by niket on 2/10/14.
 */
public interface XlsRowToXFormat<X extends XFormat> extends Function<Row, X> {
}
