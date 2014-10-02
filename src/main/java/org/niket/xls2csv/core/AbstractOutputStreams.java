package org.niket.xls2csv.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by niket on 2/10/14.
 */
public abstract class AbstractOutputStreams<S extends OutputStream> implements OutputStreams<S> {
    private static final Logger Log = LoggerFactory.getLogger(AbstractOutputStreams.class);

    private final String userId;
    private final String problemId;
    private final ConcurrentMap<String, S> streams;

    public AbstractOutputStreams(String userId, String problemId) {
        this.userId = userId;
        this.problemId = problemId;
        streams = new ConcurrentHashMap<>();
    }

    @Override
    public S addOutputStream(String streamId) {
        Log.info("Creating OutputStream, Name:{}", streamId);
        return streams.computeIfAbsent(streamId, id -> {
            try {
                return createOutputStream(id);
            } catch (Exception e) {
                Log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });
    }

    protected abstract S createOutputStream(String streamId) throws Exception;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getProblemId() {
        return problemId;
    }

    @Override
    public void close() throws IOException {
        streams.forEach((streamId, stream) -> {
            try {
                stream.flush();
                stream.close();
                Log.info("Finished writing to Stream: {}", streamId);
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public Iterator<Map.Entry<String, S>> iterator() {
        return streams.entrySet().iterator();
    }
}
