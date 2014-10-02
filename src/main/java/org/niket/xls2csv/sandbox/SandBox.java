package org.niket.xls2csv.sandbox;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Created by niket on 2/10/14.
 */
public interface SandBox<V> {
    public V execute(SandBoxedTask<V> sandBoxedTask)
            throws InterruptedException, ExecutionException, TimeoutException;

    void shutdown();
}
