package org.niket.xls2csv.core;

import java.io.Closeable;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by niket on 2/10/14.
 */
public interface OutputStreams<S extends OutputStream> extends Closeable, Iterable<Map.Entry<String, S>> {
    public OutputStreams<S> init();

    S addOutputStream(String streamId);

    String getUserId();

    String getProblemId();
}
