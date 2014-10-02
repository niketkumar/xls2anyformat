package org.niket.xls2csv.sandbox;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by niket on 2/10/14.
 */
@Singleton
public class SandBoxConfig {
    private final long timeoutValue;
    private final TimeUnit timeoutUnit;

    @Inject
    SandBoxConfig(Properties properties) {
        timeoutValue = Long.valueOf(properties.getProperty("app.timeout.value", "30"));
        timeoutUnit = TimeUnit.valueOf(properties.getProperty("app.timeout.unit", "SECONDS"));
    }

    public long getTimeoutValue() {
        return timeoutValue;
    }

    public TimeUnit getTimeoutUnit() {
        return timeoutUnit;
    }
}
