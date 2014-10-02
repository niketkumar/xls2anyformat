package org.niket.xls2csv.core;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.niket.xls2csv.core.csv.XlsRowToCsv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.Thread.currentThread;

/**
 * Created by niket on 2/10/14.
 */
public class CoreInjector extends AbstractModule {
    private static final Logger Log = LoggerFactory.getLogger(CoreInjector.class);

    @Override
    protected void configure() {
        bind(XlsRowToCsv.class);
        bind(CellToStringExtractor.class);
    }

    @Provides
    @Singleton
    Properties provideProperties() {
        try (InputStream propertiesStream = currentThread().getContextClassLoader()
                .getResourceAsStream("xls2csv.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            Log.info("Loaded Properties: {}", properties);
            return properties;
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
            return null;
        }
    }

}
