package org.niket.xls2csv.sandbox;

import java.util.concurrent.Callable;

/**
 * Created by niket on 2/10/14.
 */
public interface SandBoxedTask<V> extends Callable<V> {
    @Override
    public default V call() throws Exception {
        return execute();
    }

    public V execute() throws Exception;
}
