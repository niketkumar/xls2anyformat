package org.niket.xls2csv.core;

import java.io.ByteArrayOutputStream;

/**
 * Created by niket on 2/10/14.
 */
public class StringOutputStreams extends AbstractOutputStreams<ByteArrayOutputStream> {

    public StringOutputStreams(String userId, String problemId) {
        super(userId, problemId);
    }

    @Override
    protected ByteArrayOutputStream createOutputStream(String streamId) throws Exception {
        return new ByteArrayOutputStream();
    }

    @Override
    public OutputStreams init() {
        return this;
    }
}
