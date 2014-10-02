package org.niket.xls2csv.core;

import org.apache.poi.ss.usermodel.Cell;

import javax.inject.Singleton;
import java.util.function.Function;

/**
 * Created by niket on 2/10/14.
 */
public interface CellValueExtractor<V> extends Function<Cell, V> {
}
