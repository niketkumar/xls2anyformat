package org.niket.xls2csv.sandbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

/**
 * First implementation of SandBox interface.
 * Created by niket on 2/10/14.
 */
@Singleton
class SandBoxImpl<V> implements SandBox<V> {
    private static final Logger Log = LoggerFactory.getLogger(SandBoxImpl.class);

    private final ExecutorService executorService;
    private final SandBoxConfig sandBoxConfig;

    @Inject
    SandBoxImpl(ExecutorService executorService, SandBoxConfig sandBoxConfig) {
        this.executorService = executorService;
        this.sandBoxConfig = sandBoxConfig;
    }

    @Override
    public V execute(SandBoxedTask<V> sandBoxedTask)
            throws InterruptedException, ExecutionException, TimeoutException {
        Log.info("Started Task: {}", sandBoxedTask);
        V v = executorService.submit(sandBoxedTask)
                .get(sandBoxConfig.getTimeoutValue(), sandBoxConfig.getTimeoutUnit());
        Log.info("Finished Task: {}", sandBoxedTask);
        return v;
    }

    @Override
    public void shutdown() {
        executorService.shutdownNow();
    }
}
