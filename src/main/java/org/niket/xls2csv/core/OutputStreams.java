package org.niket.xls2csv.core;

import java.io.Closeable;
import java.io.OutputStream;
import java.util.Map;

/**
 * Collection of OutputStreams which contain data in target format. One OutputStream per Sheet.
 * Created by niket on 2/10/14.
 */
public interface OutputStreams<S extends OutputStream> extends Closeable, Iterable<Map.Entry<String, S>> {
    public OutputStreams<S> init();

    S addOutputStream(String streamId);

    public String getUserId();

    public String getProblemId();
}
