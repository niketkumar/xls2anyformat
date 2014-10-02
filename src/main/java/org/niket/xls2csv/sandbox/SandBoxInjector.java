package org.niket.xls2csv.sandbox;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.currentThread;

/**
 * Created by niket on 2/10/14.
 */
public class SandBoxInjector extends AbstractModule {
    private static final Logger Log = LoggerFactory.getLogger(SandBoxInjector.class);

    @Override
    protected void configure() {
        bind(SandBox.class).to(SandBoxImpl.class);
        bind(SandBoxConfig.class);
    }

    @Provides
    ExecutorService provideExecutorService(Properties properties) {
        return Executors.newFixedThreadPool(Integer.valueOf(properties.getProperty("sandbox.threadpool.size")));
    }

}
