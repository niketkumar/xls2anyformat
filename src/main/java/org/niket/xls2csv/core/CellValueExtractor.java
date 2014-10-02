package org.niket.xls2csv.core;

import org.apache.poi.ss.usermodel.Cell;

import java.util.function.Function;

/**
 * Given a Cell, returns the value in Generic type, V.
 * Created by niket on 2/10/14.
 */
public interface CellValueExtractor<V> extends Function<Cell, V> {
}
