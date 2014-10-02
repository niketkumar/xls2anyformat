package org.niket.xls2csv.core;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Properties;

/**
 * Configurations for Core module.
 * Created by niket on 2/10/14.
 */
@Singleton
public class CoreConfig {

    private final int maxSheetsAllowed;
    private final String dateFormat;

    @Inject
    CoreConfig(Properties properties) {
        maxSheetsAllowed = Integer.valueOf(properties.getProperty("max.sheets.allowed", "1"));
        dateFormat = properties.getProperty("date.format", "yyyy.MM.dd HH:mm:ss z");
    }

    public int getMaxSheetsAllowed() {
        return maxSheetsAllowed;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
